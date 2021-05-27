<?php

    include_once 'database.php';

    $db = new Database();

    $book_id = "";$account_id= "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }
        $select_book = "select * from book where id = $book_id limit 1";
        $result = $db->select($select_book);
        $list_book = [];
        if ($result != false){
            $row = $result->fetch_assoc();
            $item = ['id'=>$row['id'], 'name'=>$row['name'], 
            'author'=>$row['author'], 'status'=>$row['status'],
            'amount'=>$row['amount'], 'img'=>$row['img'], 
            'des'=>$row['description']];
            $select_bookmark = "select * from bookmark where book_id =$book_id and account_id = $account_id";
            $result_bookmark = $db->select($select_bookmark);
            $status_bookmark = "0"; $page_curr="1";
            if ($result_bookmark != false){
                    $row_bookmark = $result_bookmark->fetch_assoc();
                    $status_bookmark = $row_bookmark['status'];
                    $page_curr = $row_bookmark['page'];

            } 
            $item['status_bookmark'] = $status_bookmark;
            $item['page_curr'] = $page_curr;


            echo json_encode($item);
        } else {
            echo json_encode(['status'=>'khong co']);
        }

        

    }


?>