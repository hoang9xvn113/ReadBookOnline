<?php

    include_once 'database.php';

    $db = new Database();

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        $select_book = "select * from book order by update_at desc";
        $result = $db->select($select_book);
        $list_book = [];
        if ($result != false){

            while($row = $result->fetch_assoc()){
                $item = ['id'=>$row['id'], 'name'=>$row['name'], 
                            'author'=>$row['author'], 'status'=>$row['status'],
                            'amount'=>$row['amount'], 'img'=>$row['img']];
                array_push($list_book, $item);
            }

            echo json_encode($list_book);
        } else {
            echo json_encode(['status'=>'khong co']);
        }

        

    }


?>