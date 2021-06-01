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
<h2>Bảng Quản Lý Tài Khoản</h2>
<body>
    <div id="main">
        <div id="header">
            <!-- Begin: nav -->
            <ul id="nav">
                <li><a href="index.php" >Trang chủ</a></li>
                <li><a href="account.php" >Quản Lý Tài Khoản</a></li>
                <li><a href="book.php" >Quản Lý Sách </a></li>           
            </ul>          
        </div>    
</div>

    <div id="tab_1">
        <table border="1">  
            <tr><th>STT</th></th><th>Tên người dùng</th><th>Tên tài khoản</th><th>Mật khẩu</th><th>Email</th></tr>  
            <tbody>
                <?php include_once('getAccount.php') ?>
            

                </tbody>
            </table>  
        </div id="tab_2">
        <h2>Tổng số tài khoản đã đăng ký là: <?php echo $i-1 ?></h2>
</body>
</html>