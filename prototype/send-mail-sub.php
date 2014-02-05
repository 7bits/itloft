<?php
    $config = parse_ini_file("./mail.config");
    
    $notEmailSub=false;
    $emailSub = $_POST['emailSub'];

    if($emailSub=="") {
        $notEmailSub=true;
        //echo 'emailSub empty';
    }
    if( $notEmailSub ) {
        header("HTTP/1.0 406 Not Acceptable");
        $result=array('notEmailSub'=>$notEmailSub, 'emailSub'=>$emailSub);
        echo json_encode($result);
    }
    else {
        $data = date("r");
        $result = mail($config[mail], 'IT-LOFT', "Подписка на события: \n\n email = $emailSub \n data = $data", "From: $config[mail]");
        $sendRequest = false;

        if($result) {
            $sendRequest = true;
        }
        header("HTTP/1.0 200 OK");
        $result=array('notEmailSub'=>$notEmailSub, 'sendRequest'=>$sendRequest);
        echo json_encode($result);
    }
?>