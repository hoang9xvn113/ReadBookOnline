<?php

    include_once 'database.php';

    $db = new Database();

    $book_id = "";$account_id= "";$status_bookmark= "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }
        if (isset($_GET['status_bookmark'])){
            $status_bookmark = $_GET['status_bookmark'];
        }
        $status = 1;
        $insert_bookmark = "insert into bookmark(account_id, book_id) values ($account_id,$book_id)";
        if ($db->insert($insert_bookmark) == false){
            if ($status_bookmark == 1){
                $status = 0;
            }else {
                $status = 1;
            }
            $update_bookmark = "update bookmark set status = $status where book_id=$book_id and account_id=$account_id";
            $db->update($update_bookmark);
        }
        echo $status;

        

    }


?>