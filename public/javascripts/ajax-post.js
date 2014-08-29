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
        var $jsResponse = $("#js-response");
        $jsResponse.css("display", "none");
        var $form = $("#js-request-form");
        $form.find("[class*='js-error-']").html("");
//        $inputs = $form.find(":input");
//        var msgData = {};
//        for(var i = 0; i < $inputs.length; i++) {
//            var inputName = $inputs.eq(i).attr("name");
//            msgData['requesterForm.' + inputName] = $inputs.eq(i).val();
//        }

        $form.ajaxSubmit({
            url: requestUrl,
            type: "POST",
            dataType: "json",
            success: function(response) {
                if (response.status == "SUCCESS") {
                    $("#js-response").css("color", "green");
                    $("#js-request-form")[0].reset();
                    setTimeout(function() {
                      $("#js-response").css("display", "none");
                    }, 5000);
                } else {
                    var errors = response.errors;
                    for (var key in errors) {
                      $form.find(".js-error-" + key).html(errors[key]);
                    }
                    $("#js-response").css("color", "red");
                }
                $jsResponse.html(response.message);
                $jsResponse.css("display", "block");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $jsResponse.css("color", "red");
                $jsResponse.html(textStatus + ": " + errorThrown);
                $jsResponse.css("display", "block");

                console.log(jqXHR);
                console.log(jqXHR.responseText);
            }
        })
    });
});