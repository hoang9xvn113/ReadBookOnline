<?php

    include_once 'database.php';

    $db = new Database();

    $book_id = "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        $select_pages = "select * from content
                         where book_id = $book_id
                         order by page desc";
        $result = $db->select($select_pages);
        $list_book = [];
        if ($result != false){
            while($row = $result->fetch_assoc()){
                $item = ['book_id'=>$book_id, 'page'=>$row['page']];
                array_push($list_book, $item);
            }

            echo json_encode($list_book);
        } else {
            echo json_encode(['status'=>'khong co']);
        }

        

    }

?>