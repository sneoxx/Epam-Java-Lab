<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <head>
   <title>$Title$</title>
 </head>
 <body>

    <h1>Create Customer</h1>
    <a href="supplier?companyname=Hello&phone=125894">Создать поставщика</a>

<h1>Create Customer1131</h1>
  <form method="POST" name="createSupplier" action="supplier">
      Имя поставщика: <input name="companyname"><br>
      Пароль: <input name="phone"><br>
      <input type="submit" value="Создать поставщика">
  </form>

      <h1>Get Customer</h1>
      <a href="supplier?id=1">Получить поставщика</a>

  </body>

</html>

