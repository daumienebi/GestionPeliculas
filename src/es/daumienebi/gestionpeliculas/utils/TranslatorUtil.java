package es.daumienebi.gestionpeliculas.utils;
import java.util.ResourceBundle;

import es.daumienebi.gestionpeliculas.controllers.MovieManagementUIController;
import es.daumienebi.gestionpeliculas.views.ActorDetailsUI;
import es.daumienebi.gestionpeliculas.views.ActorManagementUI;
import es.daumienebi.gestionpeliculas.views.AddActorToMovieUI;
import es.daumienebi.gestionpeliculas.views.AddActorUI;
import es.daumienebi.gestionpeliculas.views.AddMovieUI;
import es.daumienebi.gestionpeliculas.views.ConfigUI;
import es.daumienebi.gestionpeliculas.views.HomeScreen;
import es.daumienebi.gestionpeliculas.views.MovieDetailsUI;
import es.daumienebi.gestionpeliculas.views.MovieManagementUI;
import es.daumienebi.gestionpeliculas.views.PersonalizedReportsUI;

public class TranslatorUtil {
	public static ResourceBundle bundle;
	
	private static void setBundle(int lang_id) {
		switch(lang_id) {
		case 1:
			bundle = ResourceBundle.getBundle("languages.language_en_GB");
			break;
		case 2:
			bundle = ResourceBundle.getBundle("languages.language_es_ES");
			break;
			
			default:bundle = ResourceBundle.getBundle("languages.language_en_GB");
		}
	}
	
	public static void translateAddActorUI(int lang_id) {
		setBundle(lang_id);
		AddActorUI.AddActor_lblName.setText(bundle.getString("AddActor_Name"));
		AddActorUI.AddActor_lblDateOfBirth.setText(bundle.getString("AddActor_DateOfBirth"));
		AddActorUI.AddActor_lblDateFormat.setText(bundle.getString("AddActor_DateFormat"));
		AddActorUI.AddActor_lblSurname.setText(bundle.getString("AddActor_Surname"));
		AddActorUI.btnAdd.setText(bundle.getString("AddActor_btnAddActor"));
		AddActorUI.btnSave.setText(bundle.getString("AddActor_btnSaveActor"));
		AddActorUI.btnAddImage.setText(bundle.getString("AddActor_btnAddImage"));
		AddActorUI.AddActor_windowTitle = bundle.getString("AddActor_windowTitle");
	}
	
	public static void translateHomeScreen(int lang_id) {
		setBundle(lang_id);
		HomeScreen.genreMenu.setText(bundle.getString("menuOptionGenre"));
		HomeScreen.homeMenu.setText(bundle.getString("menuOptionHome"));
		HomeScreen.settingsMenu.setText(bundle.getString("menuOptionSettings"));
		HomeScreen.genreMenu.setText(bundle.getString("menuOptionGenre"));
		HomeScreen.helpMenu.setText(bundle.getString("menuOptionHelp"));
		HomeScreen.menuHelpContents.setText(bundle.getString("menuOptionHelpContents"));
		HomeScreen.menuOptionAddActor.setText(bundle.getString("menuOptionAddActor"));
		HomeScreen.reportMenu.setText(bundle.getString("menuOptionReports"));
		HomeScreen.movieMenu.setText(bundle.getString("menuOptionMovie"));
		HomeScreen.actorMenu.setText(bundle.getString("menuOptionActor"));
		HomeScreen.refreshGui.setText(bundle.getString("menuOptionRefreshGUI"));
		HomeScreen.menuConfigCon.setText(bundle.getString("menuOptionConfigureConnection"));
		HomeScreen.dbMenu.setText(bundle.getString("menuOptionConnection"));
		HomeScreen.menuHelpContents.setText(bundle.getString("menuOptionHelpContents"));
		HomeScreen.menuOptionSpanish.setText(bundle.getString("menuOptionSpanish"));
		HomeScreen.menuPersonalizedReports.setText(bundle.getString("menuOptionPersonalizedReports"));
		HomeScreen.menuOptionRefresh.setText(bundle.getString("menuOptionRefresh"));
		HomeScreen.BasicReports_Movies.setText(bundle.getString("BasicReports_Movies"));
		HomeScreen.BasicReports_Actors.setText(bundle.getString("BasicReports_Actors"));
		HomeScreen.BasicReports_Genres.setText(bundle.getString("BasicReports_Genres"));
		HomeScreen.menuOptionMovieMng.setText(bundle.getString("menuOptionMovieManagement"));
		HomeScreen.menuoptionExit.setText(bundle.getString("menuOptionExit"));
		HomeScreen.menuOptionBasicReports.setText(bundle.getString("menuOptionBasicReports"));
		HomeScreen.menuOptionAddMovie.setText(bundle.getString("menuOptionAddMovie"));
		HomeScreen.menuOptionActorMng.setText(bundle.getString("menuOptionActorMng"));
		HomeScreen.menuOptionGenreMng.setText(bundle.getString("menuOptionGenreManagement"));
		HomeScreen.menuOptionEnglish.setText(bundle.getString("menuOptionEnglish"));
		HomeScreen.menuSelectLanguage.setText(bundle.getString("menuSelectLanguage"));
	}

	public static void translateAddMovieUI(int lang_id) {
		setBundle(lang_id);
		AddMovieUI.AddMovie_btnAddActors.setText(bundle.getString("AddMovie_btnAddActors"));
		AddMovieUI.AddMovie_btnAddMovie.setText(bundle.getString("AddMovie_btnAddMovie"));
		AddMovieUI.AddMovie_btnSaveMovie.setText(bundle.getString("AddMovie_btnSaveMovie"));
		AddMovieUI.AddMovie_valid_lblRating.setText(bundle.getString("AddMovie_valid_lblRating"));
		AddMovieUI.AddMovie_lblTitle.setText(bundle.getString("AddMovie_Title"));
		AddMovieUI.AddMovie_lblDuration.setText(bundle.getString("AddMovie_Duration"));
		AddMovieUI.AddMovie_lblRating.setText(bundle.getString("AddMovie_lblRating"));
		AddMovieUI.AddMovie_lblPremiereD.setText(bundle.getString("AddMovie_PremiereDate"));
		AddMovieUI.AddMovie_lblGenre.setText(bundle.getString("AddMovie_Genre"));
		AddMovieUI.AddMovie_btnAddPoster.setText(bundle.getString("AddMovie_btnAddPoster"));
		AddMovieUI.AddMovie_windowTitle = bundle.getString("AddMovie_windowTitle");
	}
	
	public static void translateConfigUI(int lang_id) {
		setBundle(lang_id);
		ConfigUI.ConfigUI_btnConnect.setText(bundle.getString("ConfigUI_btnConnect"));
		ConfigUI.ConfigUI_dbName.setText(bundle.getString("ConfigUI_dbName"));
		ConfigUI.ConfigUI_chkBoxDefaultConfig.setText(bundle.getString("ConfigUI_chkBoxDefaultConfig"));
		ConfigUI.ConfigUI_dbPass.setText(bundle.getString("ConfigUI_dbPass"));
		ConfigUI.ConfigUI_Port.setText(bundle.getString("ConfigUI_Port"));
		ConfigUI.ConfigUI_dbUser.setText(bundle.getString("ConfigUI_dbUser"));
	}
	
	public static void translateMovieManagementUI(int lang_id) {
		setBundle(lang_id);
		MovieManagementUI.MovieMngment_btnEdit.setText(bundle.getString("MovieMngment_btnEdit"));
		MovieManagementUI.MovieMngment_btnDelete.setText(bundle.getString("MovieMngment_DeleteMovie"));
		MovieManagementUI.MovieMngment_lblMovieName.setText(bundle.getString("MovieMngment_MovieName"));
	}
	
	public static void translateActorManagementUI(int lang_id) {
		setBundle(lang_id);
		ActorManagementUI.ActorMngment_btnEdit.setText(bundle.getString("ActorMngment_EditActor"));
		ActorManagementUI.ActorMngment_btnDelete.setText(bundle.getString("ActorMngment_DeleteActor"));
		ActorManagementUI.ActorMngment_lblActorName.setText(bundle.getString("ActorMngment_ActorsName"));
	}
	
	public static void translateMovieDetailsUI(int lang_id) {
		setBundle(lang_id);
		MovieDetailsUI.MovieDetail_Title.setText(bundle.getString("AddMovie_Title"));
		MovieDetailsUI.MovieDetail_Duration.setText(bundle.getString("AddMovie_Duration"));
		MovieDetailsUI.MovieDetail_Rating.setText(bundle.getString("AddMovie_lblRating"));
		MovieDetailsUI.MovieDetail_PremiereD.setText(bundle.getString("AddMovie_PremiereDate"));
		MovieDetailsUI.MovieDetail_Genre.setText(bundle.getString("AddMovie_Genre"));
	}
	
	public static void translateActorDetailsUI(int lang_id) {
		setBundle(lang_id);
		ActorDetailsUI.ActorDetail_Name.setText(bundle.getString("AddActor_Name"));
		ActorDetailsUI.ActorDetail_Surname.setText(bundle.getString("AddActor_Surname"));
		ActorDetailsUI.ActorDetail_DateOfBirth.setText(bundle.getString("AddActor_DateOfBirth"));
		ActorDetailsUI.ActorDetail_MovieCount.setText(bundle.getString("ActorDetail_MovieCount"));

	}
	
	public static void translateAddActorToMovieUI(int lang_id) {
		setBundle(lang_id);
		AddActorToMovieUI.AddActorToMovie_btnAdd.setText(bundle.getString("AddActorToMovie_btnAdd"));
		AddActorToMovieUI.AddActorToMovie_Search.setText(bundle.getString("AddActorToMovie_Search"));
	}

	public static void translatePersonalizedReportsUI(int lang_id) {
			setBundle(lang_id);
			PersonalizedReportsUI.btnLoadReport1.setText(bundle.getString("PersonalizedReportsUI_btnLoadReport"));
			PersonalizedReportsUI.btnLoadReport2.setText(bundle.getString("PersonalizedReportsUI_btnLoadReport"));
			PersonalizedReportsUI.btnLoadReport3.setText(bundle.getString("PersonalizedReportsUI_btnLoadReport"));
			PersonalizedReportsUI.header1 = bundle.getString("PersonalizedReportsUI_header1");
			PersonalizedReportsUI.header2 = bundle.getString("PersonalizedReportsUI_btnLoadReport");
			PersonalizedReportsUI.lblRating.setText(bundle.getString("PersonalizedReportsUI_lblRating"));
			PersonalizedReportsUI.lblPremiereYear.setText(bundle.getString("PersonalizedReportsUI_lblPremiereYear"));
			PersonalizedReportsUI.lblMovieCount.setText(bundle.getString("PersonalizedReportsUI_lblPremiereYear"));

	}

	public static ResourceBundle getBundle(int lang_id) {
		if(bundle == null) {
			setBundle(lang_id);
			return bundle;
		}
		return bundle;
	}
}
