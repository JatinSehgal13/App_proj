<?php
include 'connect.php';
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $id = $_POST['id'];
  $sql = "DELETE FROM students WHERE id=$id";
  if ($conn->query($sql) === TRUE) {
    echo "<h3>Record Deleted!</h3><a href='index.html'>Go Back</a>";
  } else {
    echo "Error deleting record: " . $conn->error;
  }
}
?>
<form method="POST">
  <h2>Delete Student</h2>
  <input type="text" name="id" placeholder="Student ID" required>
  <button type="submit">Delete</button>
</form>
