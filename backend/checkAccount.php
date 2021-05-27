<?php

    include_once "database.php";
    header("Content-type: text/html; charset=utf-8");

    $db = new Database();

    $id = $name = $user = $password = $email = "";

    if ($_SERVER['REQUEST_METHOD'] == "GET"){
        if (isset($_GET['user'])){
            $user = $_GET['user'];
        }
        if (isset($_GET['password'])){
            $password = $_GET['password'];
        }
        

        $select_account = "select * from account where user='$user' and password='$password' limit 1";
        $result = $db->select($select_account);
        if ($result != false) {
            $row = $result->fetch_assoc();
            echo json_encode(['id'=>$row['id'],'account_name'=>$row['name'], 'status'=>1]);
        } else {
            echo json_encode(['status'=>0]);
        }
    }
?>