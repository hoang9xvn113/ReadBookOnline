<?php
    include_once 'database.php';

    $db = new Database();

    $name = $user = $password = $email = "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['name'])){
            $name = $_GET['name'];
        }
        if (isset($_GET['user'])){
            $user = $_GET['user'];
        }
        if (isset($_GET['password'])){
            $password = $_GET['password'];
        }
        if (isset($_GET['email'])){
            $email = $_GET['email'];
        }

        $insert_account = "insert into account(name,user,password,email) values(N'$name','$user','$password','$email')";

        if ($db->insert($insert_account) == true){
            echo json_encode(["status"=>1]);
        } else {
            echo json_encode(["status"=>0]);
        }

    }
?>