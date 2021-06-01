<?php
    include_once('database.php');
    
    $db = new Database();
    
    $book_id = "";
    
    if ($_SERVER['REQUEST_METHOD'] == 'GET'){
        if (isset($_GET['book_id'])){
            $book_id = $_GET['book_id'];
        }
    }
    
    $select_page = "select amount from book where id=$book_id";
    $page = $db->select($select_page)->fetch_assoc()['amount'] + 1;
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
    <h2>Thêm Nội Dung</h2>
    <div id="main">
        <div id="header">
        
            <ul id="nav">
               
                <li><a href="account.php" >Quản Lý Tài Khoản</a></li>
                <li><a href="book.php" >Quản Lý Sách </a></li>           
            </ul>          
        </div>    
</div>
    <div >
        <form action="addBook_control.php">
            <input type="text" name="book_id" value="<?php echo $book_id ?>" hidden><br>
            Tên trang: 
            <input type="text" name="page" value="<?php echo $page ?>" placeholder="Trang"><br>
            Nội dung:
            <input type="text" name="content" placeholder="Thêm nội dung"><br>
            <input class="btn" type="submit" value="Thêm">
            </form>       
    </div>
</body>
</html>