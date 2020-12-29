Endpoint:

	Persona Normale:
	POST /utente/login					-> Richiesta di login
	POST /utente						-> Richiesta di registrazione come GUEST

	Guest:
	GET /circolo						-> Lista di tutti i circoli approvati come creazione
	POST /proposta/partecipazione/{idc}/{idu}/{l}		-> Richiesta di partecipazione al circolo
	POST /circolo/proponi/{uid}				-> Proponi un circolo al superadmin

	Super Admin:
	GET /proposta/inLavorazione				-> Lista di tutte le proposte di creazione dei circoli che sono pending
	PUT /proposta/approva/{idc}/{idu}			-> Approva una richiesta di creazione di un circolo
	PUT /proposta/rifiuta/{idc}/{idu}			-> Rifiuta una richiesta di creazione di un circolo

	Admin:
	GET /campo/{idu}					-> Lista di tutti i miei campi
	GET /partita/{idc}/{data}				-> Lista di tutte le partite dato un campo ed una data
	POST /campo/{idu}					-> Crea un campo
	PUT /campo/{idu}					-> Aggiorna un campo
	DELETE /campo/{idu}/{idc}				-> Dismetti campo
	PUT /partita/paga/{idp}/{idu}				-> Paga partita
	POST /notizia/{idu}					-> Inserisci notizia
	PUT /notizia/{idu}					-> Modifica notizia
	DELETE /notizia/{idn}/{idu}				-> Cancella notizia
	GET /proposta/iscrizione/{idu}				-> Recupera tutte le proposte di iscrizione al mio circolo
	PUT /proposta/iscrizione/accetta/{idc}/{idu}/{ida} 	-> Approva richiesta di partecipazione al tuo circolo
	PUT /proposta/iscrizione/disapprova/{idc}/{idu}/{ida}	-> Rifiuta richiesta di partecipazione al tuo circolo
	

