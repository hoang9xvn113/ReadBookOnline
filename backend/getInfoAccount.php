<?php

    include_once "database.php";
    header("Content-type: text/html; charset=utf-8");

    $db = new Database();

    $account_id = "1";

    if ($_SERVER['REQUEST_METHOD'] == "GET"){
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }

        if ($account_id == "") {
            echo json_encode(['status'=>0]);
        } else {
            $select_account = "select * from account where id=$account_id limit 1";
            $result = $db->select($select_account);
            if ($result != false) {
                $row = $result->fetch_assoc();
                echo json_encode(["name"=>$row['name'],"user"=>$row['user'], "password"=>$row['password'], "email"=>$row['email']]);
            } else {
                echo json_encode(['status'=>0]);
            }
        }
    }
?>