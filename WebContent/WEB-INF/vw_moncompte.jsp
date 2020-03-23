<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon compte</title>
<link href="assets/css/mystyle.css" rel="stylesheet">
</head>
<body>
	<header>
	<form method="POST" action="MyController">
		<input type="hidden" name="action" value="disconnect">
		<button class="btn-logout form-btn">Déconnexion</button>
	</form>
	<h2>
		<c:if test="${!empty sessionScope.connected_user}">
			<c:out value="${sessionScope.connected_user.prenom}"></c:out>
			<c:out value="${sessionScope.connected_user.nom}"></c:out>
		</c:if>
		- Mon compte
	</h2>
	</header>
	<section> <article>
	<div class="fieldset">
		<div class="fieldset_label">
			<span>Vos informations personnelles</span>
		</div>
		<div class="field">
			<label>N° compte : </label><span> <c:out
					value="${sessionScope.connected_user.nom}" />
			</span>
		</div>
		<div class="field">
			<label>Solde : </label><span> <c:out
					value="${sessionScope.connected_user.prenom}" />
			</span>
		</div>
		<div class="field">
			<label>Login : </label><span> <c:out
					value="${sessionScope.connected_user.login}" />
			</span>
		</div>
		<div class="field">
			<label>Profil : </label><span> <c:out
					value="${sessionScope.connected_user.profil}" />
			</span>
		</div>
	</div>
	</article> <article>
	<div class="fieldset">
		<div class="fieldset_label">
			<span>Votre compte</span>
		</div>
		<div class="field">
			<label>N° compte : </label><span> <c:out
					value="${sessionScope.connected_user.numero}" />
			</span>
		</div>
		<div class="field">
			<label>Solde : </label><span> <c:out
					value="${sessionScope.connected_user.solde}" />
			</span>
		</div>
	</div>
	</article> <article>
	<form method="POST" action="MyController">
		<input type="hidden" name="action" value="transfert">
		<div class="fieldset">
			<div class="fieldset_label">
				<span>Transférer de l'argent</span>
			</div>
			<div class="field">
				<label>N° compte destinataire : </label><input type="text" size="20"
					name="destination">
			</div>
			<div class="field">
				<label>Montant à transférer : </label><input type="text" size="10"
					name="montant">
			</div>
			<button class="form-btn">Transférer</button>
			<c:if test="${!empty trf_ok}">
				<p>
					<c:out value="${trf_ok}"></c:out>
				</p>
				<c:remove var="trf_ok" />
			</c:if>
			<c:if test="${!empty bad_mt}">
				<p>
					<c:out value="${bad_mt}"></c:out>
				</p>
				<c:remove var="bad_mt" />
			</c:if>

		</div>
	</form>
	</article> <article>
	<form method="POST" action="MyController">
		<input type="hidden" name="action" value="sendmsg">
		<div class="fieldset">
			<div class="fieldset_label">
				<span>Envoyer un message</span>
			</div>
			<div class="field">
				<label>Destinataire : </label> <select name="to">
					<c:forEach items="${listeUsers}" var="user">
						<option value="${user.id}">${user.prenom}${user.nom}</option>">
					</c:forEach>
				</select>
			</div>
			<div class="field">
				<label>Sujet : </label><input type="text" size="20" name="sujet">
			</div>
			<div class="field">
				<label>Message : </label><input type="text" size="40" name="corps">
			</div>
			<button class="form-btn">Envoyer</button>
			<c:if test="${!empty msg_ok}">
				<p>
					<c:out value="${msg_ok}"></c:out>
				</p>
				<c:remove var="msg_ok" />
			</c:if>
			<p>
				<a
					href="MyController?action=msglist&userid=${sessionScope.connected_user.id}"
					target="_blank">Mes messages reçus</a>
			</p>
		</div>
	</form>
	</article> </section>
</body>
</html>