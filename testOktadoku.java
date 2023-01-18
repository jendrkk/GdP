class testOktadoku {
	public static void main (String args []) {
		Oktadoku.Variante v = Oktadoku.Variante.normal;
		if (args.length > 0 && args[0].equals("-x"))
		    v = Oktadoku.Variante.mitDiagonalen;
		Oktadoku s = new Oktadoku(v);
	
		s.read();
		s.write();
		if (s.check())
			s.solve();
		else
			System.out.println("nicht ok");
	}
}
	
