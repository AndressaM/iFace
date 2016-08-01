package iFace;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sistema begin = new Sistema();
		Usuario and = new Usuario("Andressa", "andresa.mo0@gmail.com","and1", "123456", null, null);
		begin.addUsuarioHash(and);
		begin.menuI();
		
	}

}
