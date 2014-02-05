<?php
    $config = parse_ini_file("./mail.config");
    $file = "request.log";

    $notName=false;
    $notEmail=false;
    $notPhone=false;
    $name = $_POST['name'];
    $email = $_POST['email'];
    $phone = $_POST['phone'];

    if($name=="") {
        $notName=true;
        //echo 'name empty';
    }
    if($email=="") {
        $notEmail=true;
        //echo 'email empty';
    }
    if($phone=="") {
        $notPhone=true;
        //echo 'phone empty';
    }
    if( ($notName)||($notEmail)||($notPhone) ) {
        header("HTTP/1.0 406 Not Acceptable");
        $result=array('notName'=>$notName, 'notEmail'=>$notEmail, 'notPhone'=>$notPhone, 'name'=>$name, 'email'=>$email, 'phone'=>$phone);
        echo json_encode($result);
    }
    else {
        $data = date("r");

        file_put_contents($file, $name + " - " + $email + " - " + $phone + " - " + $data, FILE_APPEND | LOCK_EX);

        $result = mail($config[mail], 'IT-LOFT', "Заявка на участие: \n\n name = $name \n email = $email \n phone = $phone \n data = $data", "From: $config[mail]");
        $sendRequest = false;

        if($result) {
            $sendRequest = true;
        }
        header("HTTP/1.0 200 OK");
        $result=array('notName'=>$notName, 'notEmail'=>$notEmail, 'notPhone'=>$notPhone, 'sendRequest'=>$sendRequest);
        echo json_encode($result);
    }
?>