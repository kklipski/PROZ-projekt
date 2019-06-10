BEGIN

	generate_dvds();
	generate_clients(25);
	generate_rentals(50, 25);
	
END;