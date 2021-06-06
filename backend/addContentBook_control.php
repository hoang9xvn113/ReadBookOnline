<?php
    include_once('database.php');
    
    $db = new Database();
    $book_id = $page = $content = "";

    if ($_SERVER['REQUEST_METHOD'] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
        if (isset($_GET['page'])){
            $page = $_GET['page'];
        }
        if (isset($_GET['content'])){
            $content = $_GET['content'];
        }
        
        
        $insert_content = "insert into content(book_id, page, content) values($book_id, $page, '$content')";
        if ($book_id != ""){
            if ($content == "" || $page == "") {
                echo "Vui lòng điền đầy đủ thông tin";
            } else {
                $result = $db->insert($insert_content);
                echo "<h1>";
                if ($result){
                    $update = "update book set amount=amount+1 where id=$book_id";
                    $result_update = $db->update($update);
                    if ($result_update) {
                        echo "Thêm nội dung trang thành công";
                    }
                    
                } else{
                    echo "Thêm nội dung trang thất bại";
                }
                echo "</h1>";
            }
        } 
        
        echo "<a href='https://hochoihamhoc.000webhostapp.com/'>Trở lại trang chủ</a>";
    }

?>
