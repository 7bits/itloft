$(document).ready(function() {

    $("#js-req-success").css("display", "none");
    $("#js-req-error").css("display", "none");

    $("#js-req-success-sub").css("display", "none");
    $("#js-req-error-sub").css("display", "none");

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

    $("#js-submit-sub").bind("click", function(e) {

        e.preventDefault();
        $("#js-req-success-sub").css("display", "none");
        $("#js-req-error-sub").css("display", "none");

        emailVal=$("#js-input-email-sub").val();

        msgData = {
            emailSub: emailVal
        }

        $.ajax({
            url: "send-mail-sub.php",
            type: "POST",
            dataType: "json",
            data: msgData,
            success: function(data) {
                $("#js-req-success-sub").css("display", "block");
                $("#js-req-error-sub").css("display", "none");
            },
            error: function(jqXHR) {
                $("#js-req-success-sub").css("display", "none");
                $("#js-req-error-sub").css("display", "block");
            }
        })
    });
});