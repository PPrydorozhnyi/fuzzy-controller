let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);

    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
}

function clearEvents() {
    $("#simRes").val("");
}

function connect() {
    let socket = new SockJS('/fuzzy');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/fpw', function (greeting) {
            showResult(greeting.body);
        });
        setInterval(sendSignal, 1500);
    }, (e) => {
        let body = e.body
        if (body) {
            showError(e.body);
        } else {
            showError(e);
        }
        setConnected(false);
    });
}

function showError(message) {
    let value = $("#simRes").val();
    $("#simRes").val("Unexpected error: " + message + "\n" + value);
}

function showResult(message) {
    let parsedMessage = JSON.parse(message);
    let value = $("#simRes").val();
    $("#simRes").val("Engine speed: " + parsedMessage.engineSpeed + " RPM. Vacuum pressure: "
        + parsedMessage.vacuumPressure + " kPa. Width of the pulse applied to the fuel injector(FPW): "
        + parsedMessage.fpw + " ms.\n\n" + value);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });

    $("#send").click(function () {
        sendSignal();
    });

    $("#clearEvents").click(function () {
        clearEvents();
    });
});


function sendSignal() {

    let engineSpeed = $("#engineSpeed").val();
    if (engineSpeed < 0) {
        engineSpeed = 0;
    } else if (engineSpeed > 3000) {
        engineSpeed = 3000;
    }

    let vacuumPressure =  $("#vacuumPressure").val();

    if (vacuumPressure < 0) {
        vacuumPressure = 0;
    } else if (vacuumPressure > 40) {
        vacuumPressure = 40
    }

    let request = {
        engineSpeed: engineSpeed,
        vacuumPressure: vacuumPressure
    }

    stompClient.send('/signal', {}, JSON.stringify(request));

}
