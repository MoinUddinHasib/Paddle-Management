Endpoint:
	Persona Normale:
	POST /utente/login				-> Richiesta di login
	POST /utente					-> Richiesta di registrazione come GUEST

	Guest:
	GET /circolo					-> Lista di tutti i circoli approvati come creazione
	POST /proposta/partecipazione/{idc}/{idu}/{l}	-> Richiesta di partecipazione al circolo
	POST /circolo/proponi/{uid}			-> Proponi un circolo al superadmin

	Super Admin:
	GET /proposta/inLavorazione			-> Lista di tutte le proposte di creazione dei circoli che sono pending
	PUT /proposta/approva/{idc}/{idu}		-> Approva una richiesta di creazione di un circolo
	PUT /proposta/rifiuta/{idc}/{idu}		-> Rifiuta una richiesta di creazione di un circolo

