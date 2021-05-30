<?php
    include_once('database.php');
    
    $db = new Database();

    if ($_SERVER['REQUEST_METHOD'] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['name'])){
            $name = $_GET['name'];
        }
        if (isset($_GET['author'])){
            $author = $_GET['author'];
        }
        if (isset($_GET['category'])){
            $category = $_GET['category'];
        }
        if (isset($_GET['des'])){
            $des = $_GET['des'];
        }
        
        if ($book_id != ""){
            $update_book = "update book set name='$name', author='$author', category_id=$category, description='$des' where id = $book_id";
            $result = $db->update($update_book);
            echo "<h1>";
            if ($result){
                echo "Cập nhật thành công";
            } else {
                echo "Cập nhật thất bại";
            }
            echo "</h1>";
            echo "<a href='https://hochoihamhoc.000webhostapp.com/'>Trở lại trang chủ</a>";
        }

    }
?>