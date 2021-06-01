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
            <?php include_once('book_add_control.php') ?>
            
    </div>
</div>
</body>