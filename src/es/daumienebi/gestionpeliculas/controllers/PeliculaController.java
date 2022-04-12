package es.daumienebi.gestionpeliculas.controllers;

public class PeliculaController {
	
	/**
	 * 
	 * @return An array with the relative routes of the images for the HomeScreen Image Slider.
	 */
	public static String[] getMovieSliderImages(){
		return new String[]{
                    "/resources/hobbit.jpg","/resources/minion2.jpg",
                    "/resources/hunger_games.jpg","/resources/themanfromuncle.jpg",
                    "/resources/interstellar.jpg",
                    "/resources/4k.jpg","/resources/machete.jpg",
                    "/resources/johnwick.jpg","/resources/minion3.jpg",
                    "/resources/warcraft.jpg","/resources/superman.jpg",
                    "/resources/bobesponja.jpg","/resources/deadpool.jpg",
                    "/resources/avengerGif.gif","/resources/minion1.jpg",               
			};
	}
}
