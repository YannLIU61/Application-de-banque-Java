<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connection</title>
<link href="assets/css/mystyle.css" rel="stylesheet">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form method="POST" action="MyController">
				<input type="hidden" name="action" value="authenticate"> <input
					type="text" name="login" placeholder="login" /> <input
					type="password" name="mdp" placeholder="mot de passe" />
				<button>login</button>
			</form>
		</div>
	</div>
</body>
</html>