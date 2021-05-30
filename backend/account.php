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
            <tr><th>Tên người dùng</th><th>Tên tài khoản</th><th>Mật khẩu</th><th>Email</th></tr>  
            <tbody>
                
            
            <?php
                include_once "database.php";
                
                $db = new Database();
                
                $select_account = "select * from account";
                $result = $db->select($select_account);
                if ($result != false){
                    while($row = $result->fetch_assoc()){
                        echo "<tr>";
                        echo "<td>" . $row['name'] . "</td>";
                        echo "<td>" . $row['user'] . "</td>";
                        echo "<td>" . $row['password'] . "</td>";
                        echo "<td>" . $row['email'] . "</td>";
                        echo "</tr>";
                    }
                }
            ?>
                </tbody>
            </table>  
        </div id="tab_2">
</body>
</html>