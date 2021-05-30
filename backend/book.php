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
<h2> Bảng Quản Lý Sách</h2>
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

<div >
    <a class='btn' href="book_add.php">Thêm sách mới</a>
    <table border="1">  
        <tr>
            <th>Tên sách</th>
            <th>Tác giả</th>
            <th>Thể loại</th>
            <th>Trạng thái</th>
            <th>Số trang</th>
            <th>Miêu tả</th>
            <th>Ngày cập nhật</th>
        <tbody>
        </tr>
        <?php
            include_once "database.php";
            
            $db = new Database();
            
            $select_account = "select book.id, book.name, author, category.name as category, status, amount, description, update_at from book, category where category_id = category.id order by update_at desc" ;
            $result = $db->select($select_account);
            if ($result != false){
                while($row = $result->fetch_assoc()){
                    echo "<tr>";
                    echo "<td>" . $row['name'] . "</td>";
                    echo "<td>" . $row['author'] . "</td>";
                    echo "<td>" . $row['category'] . "</td>";
                    echo "<td>" . $row['status'] . "</td>";
                    echo "<td>" . $row['amount'] . "</td>";
                    echo "<td>" . $row['description'] . "</td>";
                    echo "<td>" . $row['update_at'] . "</td>";
                    echo "<td><a href='book_update.php?book_id=" . $row['id'] . "' class='btn'>Sửa thông tin sách</a><a href='book_add_content.php?book_id=".  $row['id'] ."' class='btn'>Thêm nội dung sách</a></td>";
                    echo "</tr>";
                }
            }
        ?>
        </tbody>
        </table>  
    
    </div>  
    

</body>
</html>