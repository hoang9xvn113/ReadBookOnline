<?php

include_once 'database.php';

$db = new Database();

$category_id = "1";

if ($_SERVER["REQUEST_METHOD"] == 'GET'){
    if (isset($_GET['category_id'])){
        $category_id = $_GET['category_id'];
    }
    $select_book = "select * from book where category_id=$category_id order by update_at desc";
    $result = $db->select($select_book);
    $list_book = [];
    if ($result != false){

        while($row = $result->fetch_assoc()){
            $item = ['id'=>$row['id'], 'name'=>$row['name'], 'img'=>$row['img']];
            array_push($list_book, $item);
        }

        echo json_encode($list_book);
    } else {
        echo json_encode(['status'=>'khong co']);
    }

    

}
?>