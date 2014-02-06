$(document).ready(function() {

    $("#js-response-sub").css("display", "none");
    $("#js-response").css("display", "none");
    
//    subscription
    $("#js-submit-sub").bind("click", function(e) {

        e.preventDefault();
        $("#js-response-sub").css("display", "none");

        emailVal=$("#js-input-email-sub").val();

        msgData = {
            emailSub: emailVal
        }

        $.ajax({
            url: subscribeUrl,
            type: "POST",
            dataType: "json",
            data: msgData,
            success: function(response) {
                if (response.status == "SUCCESS") {
                    $("#js-response-sub").css("color", "green");
                    $("#js-subscription-form")[0].reset();
                } else {
                    $("#js-response-sub").css("color", "red");
                }
                $("#js-response-sub").html(response.message);
                $("#js-response-sub").css("display", "block");

                setTimeout(function() {
                    $("#js-response-sub").css("display", "none");
                }, 5000);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $("#js-response-sub").css("color", "red");
                $("#js-response-sub").html(textStatus + ": " + errorThrown + "; see console logs");
                $("#js-response-sub").css("display", "block");

                console.log(jqXHR);
                console.log(jqXHR.responseText);
            }
        })
    });

//    request
    $("#js-submit").bind("click", function(e) {

        e.preventDefault();
        $("#js-response").css("display", "none");

        nameVal=$("#js-input-name").val();
        emailVal=$("#js-input-email").val();
        phoneVal=$("#js-input-phone").val();

        msgData = {
            name: nameVal,
            email: emailVal,
            phone: phoneVal
        }

        $.ajax({
            url: requestUrl,
            type: "POST",
            dataType: "json",
            data: msgData,
            success: function(response) {
                if (response.status == "SUCCESS") {
                    $("#js-response").css("color", "green");
                    $("#js-request-form")[0].reset();
                } else {
                    $("#js-response").css("color", "red");
                }
                $("#js-response").html(response.message);
                $("#js-response").css("display", "block");

                setTimeout(function() {
                    $("#js-response").css("display", "none");
                }, 5000);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $("#js-response").css("color", "red");
                $("#js-response").html(textStatus + ": " + errorThrown + "; see console logs");
                $("#js-response").css("display", "block");

                console.log(jqXHR);
                console.log(jqXHR.responseText);
            }
        })
    });
});