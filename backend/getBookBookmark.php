<?php

    include_once 'database.php';

    $db = new Database();

    $account_id = "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }
        $select_book = "select *,book.status as book_status from book,bookmark
                         where account_id = $account_id and bookmark.status = 1 and id = book_id
                         order by update_at desc ";
        $result = $db->select($select_book);
        $list_book = [];
        if ($result != false){

            while($row = $result->fetch_assoc()){
                $item = ['id'=>$row['id'], 'name'=>$row['name'], 
                            'author'=>$row['author'], 'status'=>$row['book_status'],
                            'page_curr'=>$row['page'], 'img'=>$row['img']];
                array_push($list_book, $item);
            }

            echo json_encode($list_book);
        } else {
            echo json_encode(['status'=>'khong co']);
        }

        

    }

?>