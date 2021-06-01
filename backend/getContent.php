<?php

    include_once 'database.php';

    $db = new Database();

    $book_id = $page ="";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['page'])){
            $page = $_GET['page'];
        }
        
        if ($book_id=="" || $page==""){
            echo json_encode(['status'=>0]);
        } else {
            $select_content = "select * from book,content where id = $book_id and page = $page and id=book_id limit 1";
            $result = $db->select($select_content);
            if ($result != false){
                $row = $result->fetch_assoc();
                $item = ['content'=>$row['content']];
    
                echo json_encode($item);
            } else {
                echo json_encode(['status'=>0]);
            }
        }



        

    }


?>