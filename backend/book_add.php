<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhom_4</title>
    
   <link rel="stylesheet" href="./assets/css/style.css">
   <link rel="stylesheet" href="./assets/Fonts/themify-icons/themify-icons.css">
</head>

<body>
    <h2>Thêm Sách</h2>

    <div id="main">
        <div id="header">
            
            <ul id="nav">
               
                <li><a href="account.php" >Quản Lý Tài Khoản</a></li>
                <li><a href="book.php" >Quản Lý Sách </a></li>           
            </ul>          
        </div>    
</div>

<div>
    <div >
        <form action="" method="POST">
            Tên sách:
            <input type="text" name="name" placeholder="Tên sách"><br>
            Tác giả:
            <input type="text" name="author" placeholder="Tên tác giả"><br>
            Thể loại:
            <select name="category">
        		<option value="1">Tâm lý Kĩ Năng</option>
        		<option value="2">Văn học</option>
        		<option value="3">Triết học</option>
        		<option value="4">Toán học</option>
        		<option value="5">Kinh tế</option>
	    </select>
	    <br>
            Mô tả:
            <input type="text" name="des" value="" placeholder="Mô tả"><br>
            <input class="btn" type="submit" value="Thêm sách">
            </form>
            <?php
                include_once "database.php";
            
                $db = new Database();
            
                $name = $author = $category = $des = "";
            
                if ($_SERVER['REQUEST_METHOD'] == "POST"){
                    if (isset($_POST['name'])){
                        $name = $_POST['name'];
                    }
                    if (isset($_POST['author'])){
                        $author = $_POST['author'];
                    }
                    if (isset($_POST['category'])){
                        $category = $_POST['category'];
                    }
                    if (isset($_POST['des'])){
                        $des = $_POST['des'];
                    }
                    if ($name=="" || $author=="" || $category=="" || $des==""){
                        echo "Vui lòng nhập đầy đủ dữ liệu";
                    } else {
                        $select_img = "select max(img) as img from book";
                        $img = $db->select($select_img)->fetch_assoc()['img'] + 1;
                        $insert_book = "insert into book(name,author,category_id,description,img) values('$name','$author',$category,'$des',$img)";
                        $result_insert = $db->insert($insert_book);
                        if ($result_insert) {
                            echo "Thêm thành công";
                        } else {
                            echo "Thêm lỗi";
                        }
                    }
                }
            ?>
            
    </div>
</div>
</body>