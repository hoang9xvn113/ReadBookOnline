<?php
include_once('database.php');

$db = new Database();

$book_id = "";

    if ($_SERVER["REQUEST_METHOD"] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
    }
    


    $select_book = "select * from book where book.id = $book_id";
    $book = $db->select($select_book)->fetch_assoc();
?>


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
    <h2>Chỉnh Sửa Sách</h2>

    <div id="main">
        <div id="header">
            
            <ul id="nav">
               
                <li><a href="account.php" >Quản Lý Tài Khoản</a></li>
                <li><a href="book.php" >Quản Lý Sách </a></li>           
            </ul>          
        </div>    
</div>
    <div >
        <form action='updateBook_control.php' method='get'>
            <input type='text' name='book_id' value='<?php echo $book['id'] ?>' placeholder='ID sách' hidden><br>
            <label>Tên sách</label>
            <input type='text' name='name' value=' <?php echo $book['name'] ?>' placeholder='Tên sách'><br>
            <label>Tác giả</label>
            <input type='text' name='author' value=' <?php echo $book['author']  ?>'  placeholder='Tên tác giả'><br>
            <label>Thể loại</label>
            <select name='category'>
            <option value='1'>Tâm lý Kĩ Năng</option>
    		<option value='2'>Văn học</option>
    		<option value='3'>Triết học</option>
    		<option value='4'>Toán học</option>
    		<option value='5'>Kinh tế</option>
    		</select><br>
            <label>Mô tả</label>
            <input type='text' name='des' value='<?php echo $book['description'] ?>' placeholder='Mô tả'><br>
            <input class="btn" type="submit" value="Sửa thông tin sách">
        </form>
    </div>
</body>
</html>