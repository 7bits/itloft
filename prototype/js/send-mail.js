$(document).ready(function() {

    $("#js-req-success").css("display", "none");
    $("#js-req-error").css("display", "none");

    $("#js-submit").bind("click", function(e) {

        e.preventDefault();
        $("#js-req-success").css("display", "none");
        $("#js-req-error").css("display", "none");

        nameVal=$("#js-input-name").val();
        emailVal=$("#js-input-email").val();
        phoneVal=$("#js-input-phone").val();

        msgData = {
            name: nameVal,
            email: emailVal,
            phone: phoneVal
        }

        $.ajax({
            url: "send-mail.php",
            type: "POST",
            dataType: "json",
            data: msgData,
            success: function(data) {
                $("#js-req-success").css("display", "block");
                $("#js-req-error").css("display", "none");
            },
            error: function(jqXHR) {
                $("#js-req-success").css("display", "none");
                $("#js-req-error").css("display", "block");
            }
        })
    });
});