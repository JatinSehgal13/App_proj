<?php
include 'connect.php';
$result = $conn->query("SELECT * FROM students");
echo "<h2>All Students</h2><table border='1' cellpadding='10'>
<tr><th>ID</th><th>Name</th><th>Course</th></tr>";

while ($row = $result->fetch_assoc()) {
  echo "<tr><td>{$row['id']}</td><td>{$row['name']}</td><td>{$row['course']}</td></tr>";
}
echo "</table><br><a href='index.html'>Back</a>";
$conn->close();
?>
