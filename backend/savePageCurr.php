<?php

    include_once 'database.php';

    $db = new Database();

    $book_id = "";$account_id= "";$page="";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }
        if (isset($_GET['page'])){
            $page = $_GET['page'];
        }
        $insert_bookmark = "insert into bookmark values ($account_id,$book_id,0,$page)";
        if ($db->insert($insert_bookmark) == false){
            $update_bookmark = "update bookmark set page = $page where book_id=$book_id and account_id=$account_id and page<$page";
            if($db->update($update_bookmark)){
                echo "good";
            }
        } else{
            echo json_decode(['status'=>0]);
        }

    }


?>