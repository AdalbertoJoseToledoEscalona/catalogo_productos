/**
 * 
 */
package modelo.utils;

/**
 * @author adalberto
 * 
 */
public class Util {

	/**
	 * 
	 */
	public Util() {
		// TODO Auto-generated constructor stub
	}

	public static String convertirANombreCampo(String texto) {
		return texto.replaceAll(" ", "_").replaceAll("Á", "A")
				.replaceAll("É", "E").replaceAll("Í", "I").replaceAll("Ó", "O")
				.replaceAll("Ú", "U").replaceAll("á", "a").replaceAll("é", "e")
				.replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ú", "u")
				.replaceAll("Ü", "U").replaceAll("ü", "u");
	}

	/*
	 * public static Class[] getClasses(String pckgname) throws
	 * ClassNotFoundException {
	 * 
	 * ArrayList classes=new ArrayList(); // Get a File object for the package
	 * File directory=null; try { directory=new File( Thread.currentThread(
	 * ).getContextClassLoader( ).getResource( '/'+pckgname.replace( '.',
	 * '/')).getFile()); } catch(NullPointerException x) { throw new
	 * ClassNotFoundException( pckgname+" does not " +
	 * "appear to be a valid package"); }
	 * 
	 * if(directory.exists()) { // Get the list of the files contained in the
	 * package String[] files=directory.list(); for(int i=0; i<files.length;
	 * i++) { // we are only interested in .class files
	 * if(files[i].endsWith(".class")) { // removes the .class extension
	 * classes.add( Class.forName(pckgname+'.' +files[i].substring( 0,
	 * files[i].length()-6))); } } } else { throw new ClassNotFoundException(
	 * pckgname+" does not " + "appear to be a valid package"); }
	 * 
	 * Class[] classesA=new Class[classes.size()]; classes.toArray(classesA);
	 * return classesA; }
	 */

	public static void main(String[] args) {
		// try {
		Package[] ps = Package.getPackages();
		for (Package package1 : ps) {
			System.out.println(package1.getName());
		}

		// System.out.println(Util.getClasses("java.modelo"));
		/*
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}

}
