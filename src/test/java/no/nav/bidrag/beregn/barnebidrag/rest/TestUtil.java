package no.nav.bidrag.beregn.barnebidrag.rest;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import no.nav.bidrag.beregn.barnebidrag.rest.consumer.Forbruksutgifter;
import no.nav.bidrag.beregn.barnebidrag.rest.consumer.MaksFradrag;
import no.nav.bidrag.beregn.barnebidrag.rest.consumer.MaksTilsyn;
import no.nav.bidrag.beregn.barnebidrag.rest.consumer.Samvaersfradrag;
import no.nav.bidrag.beregn.barnebidrag.rest.consumer.Sjablontall;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.AntallBarnIEgetHusholdPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BarnetilsynMedStonadPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnBarnebidragGrunnlag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnBidragsevneGrunnlag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnBidragsevneResultat;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnNettoBarnetilsynGrunnlag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnNettoBarnetilsynResultat;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnSamvaersfradragGrunnlag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnSamvaersfradragResultat;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnUnderholdskostnadGrunnlag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BeregnUnderholdskostnadResultat;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.BostatusPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.FaktiskUtgiftPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ForpleiningUtgiftPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.Inntekt;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.InntektPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.Periode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatBeregningBidragsevne;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatBeregningNettoBarnetilsyn;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatBeregningSamvaersfradrag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatBeregningUnderholdskostnad;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatGrunnlagBidragsevne;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatGrunnlagFaktiskUtgift;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatGrunnlagNettoBarnetilsyn;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatGrunnlagSamvaersfradrag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatGrunnlagUnderholdskostnad;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatPeriodeBidragsevne;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatPeriodeNettoBarnetilsyn;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatPeriodeSamvaersfradrag;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.ResultatPeriodeUnderholdskostnad;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.SaerfradragPeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.SamvaersklassePeriode;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.Sjablon;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.SjablonInnhold;
import no.nav.bidrag.beregn.barnebidrag.rest.dto.http.SkatteklassePeriode;
import no.nav.bidrag.beregn.felles.dto.AvvikCore;
import no.nav.bidrag.beregn.felles.dto.PeriodeCore;
import no.nav.bidrag.beregn.felles.dto.SjablonCore;
import no.nav.bidrag.beregn.felles.dto.SjablonInnholdCore;
import no.nav.bidrag.beregn.felles.enums.SjablonInnholdNavn;
import no.nav.bidrag.beregn.felles.enums.SjablonTallNavn;
import no.nav.bidrag.beregn.nettobarnetilsyn.dto.BeregnNettoBarnetilsynResultatCore;
import no.nav.bidrag.beregn.nettobarnetilsyn.dto.FaktiskUtgiftCore;
import no.nav.bidrag.beregn.samvaersfradrag.dto.BeregnSamvaersfradragResultatCore;
import no.nav.bidrag.beregn.underholdskostnad.dto.BeregnUnderholdskostnadResultatCore;
import no.nav.bidrag.beregn.underholdskostnad.dto.ResultatBeregningCore;
import no.nav.bidrag.beregn.underholdskostnad.dto.ResultatGrunnlagCore;
import no.nav.bidrag.beregn.underholdskostnad.dto.ResultatPeriodeCore;

public class TestUtil {


  public static BeregnBarnebidragGrunnlag byggBarnebidragGrunnlag() {
    return new BeregnBarnebidragGrunnlag(byggBidragsevneGrunnlag(""), byggNettoBarnetilsynGrunnlag(""), byggUnderholdskostnadGrunnlag(""),
        byggSamvaersfradragGrunnlag(""), "Resten av grunnlaget");
  }

  public static BeregnBidragsevneGrunnlag byggBidragsevneGrunnlag() {
    return byggBidragsevneGrunnlag("");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlag() {
    return byggNettoBarnetilsynGrunnlag("");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenBeregnDatoFra() {
    return byggNettoBarnetilsynGrunnlag("beregnDatoFra");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenBeregnDatoTil() {
    return byggNettoBarnetilsynGrunnlag("beregnDatoTil");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftPeriodeListe() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftPeriodeListe");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftPeriodeDatoFraTil() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftPeriodeDatoFraTil");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftPeriodeDatoFra() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftPeriodeDatoFra");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftPeriodeDatoTil() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftPeriodeDatoTil");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftSoknadsbarnFodselsdato() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftSoknadsbarnFodselsdato");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftSoknadsbarnPersonId() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftSoknadsbarnPersonId");
  }

  public static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlagUtenFaktiskUtgiftBelop() {
    return byggNettoBarnetilsynGrunnlag("faktiskUtgiftBelop");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlag() {
    return byggUnderholdskostnadGrunnlag("");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBeregnDatoFra() {
    return byggUnderholdskostnadGrunnlag("beregnDatoFra");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBeregnDatoTil() {
    return byggUnderholdskostnadGrunnlag("beregnDatoTil");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenSoknadBarnFodselsdato() {
    return byggUnderholdskostnadGrunnlag("soknadBarnFodselsdato");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadPeriodeListe() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadPeriodeListe");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenForpleiningUtgiftPeriodeListe() {
    return byggUnderholdskostnadGrunnlag("forpleiningUtgiftPeriodeListe");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadPeriodeDatoFraTil() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadPeriodeDatoFraTil");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadPeriodeDatoFra() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadPeriodeDatoFra");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadPeriodeDatoTil() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadPeriodeDatoTil");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadTilsynType() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadTilsynType");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenBarnetilsynMedStonadStonadType() {
    return byggUnderholdskostnadGrunnlag("barnetilsynMedStonadStonadType");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenForpleiningUtgiftPeriodeDatoFraTil() {
    return byggUnderholdskostnadGrunnlag("forpleiningUtgiftPeriodeDatoFraTil");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenForpleiningUtgiftPeriodeDatoFra() {
    return byggUnderholdskostnadGrunnlag("forpleiningUtgiftPeriodeDatoFra");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenForpleiningUtgiftPeriodeDatoTil() {
    return byggUnderholdskostnadGrunnlag("forpleiningUtgiftPeriodeDatoTil");
  }

  public static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlagUtenForpleiningUtgiftBelop() {
    return byggUnderholdskostnadGrunnlag("forpleiningUtgiftBelop");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlag() {
    return byggSamvaersfradragGrunnlag("");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenBeregnDatoFra() {
    return byggSamvaersfradragGrunnlag("beregnDatoFra");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenBeregnDatoTil() {
    return byggSamvaersfradragGrunnlag("beregnDatoTil");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSoknadsbarnFodselsdato() {
    return byggSamvaersfradragGrunnlag("soknadsbarnFodselsdato");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSamvaersklassePeriodeListe() {
    return byggSamvaersfradragGrunnlag("samvaersklassePeriodeListe");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSamvaersklassePeriodeDatoFraTil() {
    return byggSamvaersfradragGrunnlag("samvaersklassePeriodeDatoFraTil");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSamvaersklassePeriodeDatoFra() {
    return byggSamvaersfradragGrunnlag("samvaersklassePeriodeDatoFra");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSamvaersklassePeriodeDatoTil() {
    return byggSamvaersfradragGrunnlag("samvaersklassePeriodeDatoTil");
  }

  public static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlagUtenSamvaersklasse() {
    return byggSamvaersfradragGrunnlag("samvaersklasse");
  }

  // Bygger opp BeregnBidragsevneGrunnlag
  private static BeregnBidragsevneGrunnlag byggBidragsevneGrunnlag(String nullVerdi) {
    var beregnDatoFra = (nullVerdi.equals("beregnDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var beregnDatoTil = (nullVerdi.equals("beregnDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var inntektDatoFra = (nullVerdi.equals("inntektDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var inntektDatoTil = (nullVerdi.equals("inntektDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var inntektType = (nullVerdi.equals("inntektType") ? null : "LØNNSINNTEKT");
    var inntektBelop = (nullVerdi.equals("inntektBelop") ? null : 100000d);
    var skatteklasseDatoFra = (nullVerdi.equals("skatteklasseDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var skatteklasseDatoTil = (nullVerdi.equals("skatteklasseDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var skatteklasse = (nullVerdi.equals("skatteklasse") ? null : 1);
    var bostatusDatoFra = (nullVerdi.equals("bostatusDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var bostatusDatoTil = (nullVerdi.equals("bostatusDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var bostatusKode = (nullVerdi.equals("bostatusKode") ? null : "MED_ANDRE");
    var antallBarnIEgetHusholdDatoFra = (nullVerdi.equals("antallBarnIEgetHusholdDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var antallBarnIEgetHusholdDatoTil = (nullVerdi.equals("antallBarnIEgetHusholdDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var antallBarn = (nullVerdi.equals("antallBarn") ? null : 1);
    var saerfradragDatoFra = (nullVerdi.equals("saerfradragDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var saerfradragDatoTil = (nullVerdi.equals("saerfradragDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var saerfradragKode = (nullVerdi.equals("saerfradragKode") ? null : "HELT");

    List<InntektPeriode> inntektPeriodeListe;
    if (nullVerdi.equals("inntektPeriodeListe")) {
      inntektPeriodeListe = null;
    } else {
      InntektPeriode inntektPeriode;
      if (nullVerdi.equals("inntektDatoFraTil")) {
        inntektPeriode = new InntektPeriode(null, inntektType, inntektBelop);
      } else {
        inntektPeriode = new InntektPeriode(new Periode(inntektDatoFra, inntektDatoTil), inntektType, inntektBelop);
      }
      inntektPeriodeListe = new ArrayList<>();
      inntektPeriodeListe.add(inntektPeriode);
    }

    List<SkatteklassePeriode> skatteklassePeriodeListe;
    if (nullVerdi.equals("skatteklassePeriodeListe")) {
      skatteklassePeriodeListe = null;
    } else {
      SkatteklassePeriode skatteklassePeriode;
      if (nullVerdi.equals("skatteklasseDatoFraTil")) {
        skatteklassePeriode = new SkatteklassePeriode(null, skatteklasse);
      } else {
        skatteklassePeriode = new SkatteklassePeriode(new Periode(skatteklasseDatoFra, skatteklasseDatoTil), skatteklasse);
      }
      skatteklassePeriodeListe = new ArrayList<>();
      skatteklassePeriodeListe.add(skatteklassePeriode);
    }

    List<BostatusPeriode> bostatusPeriodeListe;
    if (nullVerdi.equals("bostatusPeriodeListe")) {
      bostatusPeriodeListe = null;
    } else {
      BostatusPeriode bostatusPeriode;
      if (nullVerdi.equals("bostatusDatoFraTil")) {
        bostatusPeriode = new BostatusPeriode(null, bostatusKode);
      } else {
        bostatusPeriode = new BostatusPeriode(new Periode(bostatusDatoFra, bostatusDatoTil), bostatusKode);
      }
      bostatusPeriodeListe = new ArrayList<>();
      bostatusPeriodeListe.add(bostatusPeriode);
    }

    List<AntallBarnIEgetHusholdPeriode> antallBarnIEgetHusholdPeriodeListe;
    if (nullVerdi.equals("antallBarnIEgetHusholdPeriodeListe")) {
      antallBarnIEgetHusholdPeriodeListe = null;
    } else {
      AntallBarnIEgetHusholdPeriode antallBarnIEgetHusholdPeriode;
      if (nullVerdi.equals("antallBarnIEgetHusholdDatoFraTil")) {
        antallBarnIEgetHusholdPeriode = new AntallBarnIEgetHusholdPeriode(null, antallBarn);
      } else {
        antallBarnIEgetHusholdPeriode =
            new AntallBarnIEgetHusholdPeriode(new Periode(antallBarnIEgetHusholdDatoFra, antallBarnIEgetHusholdDatoTil), antallBarn);
      }
      antallBarnIEgetHusholdPeriodeListe = new ArrayList<>();
      antallBarnIEgetHusholdPeriodeListe.add(antallBarnIEgetHusholdPeriode);
    }

    List<SaerfradragPeriode> saerfradragPeriodeListe;
    if (nullVerdi.equals("saerfradragPeriodeListe")) {
      saerfradragPeriodeListe = null;
    } else {
      SaerfradragPeriode saerfradragPeriode;
      if (nullVerdi.equals("saerfradragDatoFraTil")) {
        saerfradragPeriode = new SaerfradragPeriode(null, saerfradragKode);
      } else {
        saerfradragPeriode = new SaerfradragPeriode(new Periode(saerfradragDatoFra, saerfradragDatoTil), saerfradragKode);
      }
      saerfradragPeriodeListe = new ArrayList<>();
      saerfradragPeriodeListe.add(saerfradragPeriode);
    }

    return new BeregnBidragsevneGrunnlag(beregnDatoFra, beregnDatoTil, inntektPeriodeListe, skatteklassePeriodeListe, bostatusPeriodeListe,
        antallBarnIEgetHusholdPeriodeListe, saerfradragPeriodeListe);
  }

  // Bygger opp NettoBarnetilsynGrunnlag
  private static BeregnNettoBarnetilsynGrunnlag byggNettoBarnetilsynGrunnlag(String nullVerdi) {
    var beregnDatoFra = (nullVerdi.equals("beregnDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var beregnDatoTil = (nullVerdi.equals("beregnDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var faktiskUtgiftPeriodeDatoFra = (nullVerdi.equals("faktiskUtgiftPeriodeDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var faktiskUtgiftPeriodeDatoTil = (nullVerdi.equals("faktiskUtgiftPeriodeDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var faktiskUtgiftSoknadsbarnFodselsdato = (nullVerdi.equals("faktiskUtgiftSoknadsbarnFodselsdato") ? null : LocalDate.parse("2010-01-01"));
    var faktiskUtgiftSoknadsbarnPersonId = (nullVerdi.equals("faktiskUtgiftSoknadsbarnPersonId") ? null : 1);
    var faktiskUtgiftBelop = (nullVerdi.equals("faktiskUtgiftBelop") ? null : 1000d);

    List<FaktiskUtgiftPeriode> faktiskUtgiftPeriodeListe;
    if (nullVerdi.equals("faktiskUtgiftPeriodeListe")) {
      faktiskUtgiftPeriodeListe = null;
    } else {
      FaktiskUtgiftPeriode faktiskUtgiftPeriode;
      if (nullVerdi.equals("faktiskUtgiftPeriodeDatoFraTil")) {
        faktiskUtgiftPeriode = new FaktiskUtgiftPeriode(null, faktiskUtgiftSoknadsbarnFodselsdato, faktiskUtgiftSoknadsbarnPersonId,
            faktiskUtgiftBelop);
      } else {
        faktiskUtgiftPeriode = new FaktiskUtgiftPeriode(new Periode(faktiskUtgiftPeriodeDatoFra, faktiskUtgiftPeriodeDatoTil),
            faktiskUtgiftSoknadsbarnFodselsdato, faktiskUtgiftSoknadsbarnPersonId, faktiskUtgiftBelop);
      }
      faktiskUtgiftPeriodeListe = new ArrayList<>();
      faktiskUtgiftPeriodeListe.add(faktiskUtgiftPeriode);
    }

    return new BeregnNettoBarnetilsynGrunnlag(beregnDatoFra, beregnDatoTil, faktiskUtgiftPeriodeListe);
  }

  // Bygger opp BeregnUnderholdskostnadGrunnlag
  private static BeregnUnderholdskostnadGrunnlag byggUnderholdskostnadGrunnlag(String nullVerdi) {
    var beregnDatoFra = (nullVerdi.equals("beregnDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var beregnDatoTil = (nullVerdi.equals("beregnDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var soknadBarnFodselsdato = (nullVerdi.equals("soknadBarnFodselsdato") ? null : LocalDate.parse("2010-01-01"));
    var barnetilsynMedStonadPeriodeDatoFra = (nullVerdi.equals("barnetilsynMedStonadPeriodeDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var barnetilsynMedStonadPeriodeDatoTil = (nullVerdi.equals("barnetilsynMedStonadPeriodeDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var barnetilsynMedStonadTilsynType = (nullVerdi.equals("barnetilsynMedStonadTilsynType") ? null : "DO");
    var barnetilsynMedStonadStonadType = (nullVerdi.equals("barnetilsynMedStonadStonadType") ? null : "64");
    var forpleiningUtgiftPeriodeDatoFra = (nullVerdi.equals("forpleiningUtgiftPeriodeDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var forpleiningUtgiftPeriodeDatoTil = (nullVerdi.equals("forpleiningUtgiftPeriodeDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var forpleiningUtgiftBelop = (nullVerdi.equals("forpleiningUtgiftBelop") ? null : 1000d);

    List<BarnetilsynMedStonadPeriode> barnetilsynMedStonadPeriodeListe;
    if (nullVerdi.equals("barnetilsynMedStonadPeriodeListe")) {
      barnetilsynMedStonadPeriodeListe = null;
    } else {
      BarnetilsynMedStonadPeriode barnetilsynMedStonadPeriode;
      if (nullVerdi.equals("barnetilsynMedStonadPeriodeDatoFraTil")) {
        barnetilsynMedStonadPeriode = new BarnetilsynMedStonadPeriode(null, barnetilsynMedStonadTilsynType, barnetilsynMedStonadStonadType);
      } else {
        barnetilsynMedStonadPeriode = new BarnetilsynMedStonadPeriode(new Periode(barnetilsynMedStonadPeriodeDatoFra,
            barnetilsynMedStonadPeriodeDatoTil), barnetilsynMedStonadTilsynType, barnetilsynMedStonadStonadType);
      }
      barnetilsynMedStonadPeriodeListe = new ArrayList<>();
      barnetilsynMedStonadPeriodeListe.add(barnetilsynMedStonadPeriode);
    }

    List<ForpleiningUtgiftPeriode> forpleiningUtgiftPeriodeListe;
    if (nullVerdi.equals("forpleiningUtgiftPeriodeListe")) {
      forpleiningUtgiftPeriodeListe = null;
    } else {
      ForpleiningUtgiftPeriode forpleiningUtgiftPeriode;
      if (nullVerdi.equals("forpleiningUtgiftPeriodeDatoFraTil")) {
        forpleiningUtgiftPeriode = new ForpleiningUtgiftPeriode(null, forpleiningUtgiftBelop);
      } else {
        forpleiningUtgiftPeriode = new ForpleiningUtgiftPeriode(new Periode(forpleiningUtgiftPeriodeDatoFra, forpleiningUtgiftPeriodeDatoTil),
            forpleiningUtgiftBelop);
      }
      forpleiningUtgiftPeriodeListe = new ArrayList<>();
      forpleiningUtgiftPeriodeListe.add(forpleiningUtgiftPeriode);
    }

    return new BeregnUnderholdskostnadGrunnlag(beregnDatoFra, beregnDatoTil, soknadBarnFodselsdato, barnetilsynMedStonadPeriodeListe,
        forpleiningUtgiftPeriodeListe);
  }

  // Bygger opp Samværsfradrag
  private static BeregnSamvaersfradragGrunnlag byggSamvaersfradragGrunnlag(String nullVerdi) {
    var beregnDatoFra = (nullVerdi.equals("beregnDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var beregnDatoTil = (nullVerdi.equals("beregnDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var soknadsbarnFodselsdato = (nullVerdi.equals("soknadsbarnFodselsdato") ? null : LocalDate.parse("2017-01-01"));
    var samvaersklassePeriodeDatoFra = (nullVerdi.equals("samvaersklassePeriodeDatoFra") ? null : LocalDate.parse("2017-01-01"));
    var samvaersklassePeriodeDatoTil = (nullVerdi.equals("samvaersklassePeriodeDatoTil") ? null : LocalDate.parse("2020-01-01"));
    var samvaersklasse = (nullVerdi.equals("samvaersklasse") ? null : "00");

    List<SamvaersklassePeriode> samvaersklassePeriodeListe;
    if (nullVerdi.equals("samvaersklassePeriodeListe")) {
      samvaersklassePeriodeListe = null;
    } else {
      SamvaersklassePeriode samvaersklassePeriode;
      if (nullVerdi.equals("samvaersklassePeriodeDatoFraTil")) {
        samvaersklassePeriode = new SamvaersklassePeriode(null, samvaersklasse);
      } else {
        samvaersklassePeriode = new SamvaersklassePeriode(new Periode(samvaersklassePeriodeDatoFra, samvaersklassePeriodeDatoTil),
            samvaersklasse);
      }
      samvaersklassePeriodeListe = new ArrayList<>();
      samvaersklassePeriodeListe.add(samvaersklassePeriode);
    }

    return new BeregnSamvaersfradragGrunnlag(beregnDatoFra, beregnDatoTil, soknadsbarnFodselsdato, samvaersklassePeriodeListe);
  }

  // Bygger opp BeregnBidragsevneResultat
  public static BeregnBidragsevneResultat dummyBidragsevneResultat() {
    var bidragPeriodeResultatListe = new ArrayList<ResultatPeriodeBidragsevne>();
    bidragPeriodeResultatListe.add(new ResultatPeriodeBidragsevne(new Periode(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        new ResultatBeregningBidragsevne(100d),
        new ResultatGrunnlagBidragsevne(singletonList(new Inntekt("LØNNSINNTEKT", 500000d)), 1, "MED_ANDRE", 1,
            "HELT", singletonList(new Sjablon(SjablonTallNavn.TRYGDEAVGIFT_PROSENT.getNavn(), emptyList(),
            singletonList(new SjablonInnhold(SjablonInnholdNavn.SJABLON_VERDI.getNavn(), 8d)))))));
    return new BeregnBidragsevneResultat(bidragPeriodeResultatListe);
  }

  // Bygger opp BeregnNettoBarnetilsynResultat
  public static BeregnNettoBarnetilsynResultat dummyNettoBarnetilsynResultat() {
    var bidragPeriodeResultatListe = new ArrayList<ResultatPeriodeNettoBarnetilsyn>();
    bidragPeriodeResultatListe.add(new ResultatPeriodeNettoBarnetilsyn(new Periode(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        singletonList(new ResultatBeregningNettoBarnetilsyn(1, 100)),
        new ResultatGrunnlagNettoBarnetilsyn(singletonList(new ResultatGrunnlagFaktiskUtgift(LocalDate.parse("2010-01-01"), 1, 100d)),
            emptyList())));
    return new BeregnNettoBarnetilsynResultat(bidragPeriodeResultatListe);
  }

  // Bygger opp BeregnNettoBarnetilsynResultatCore
  public static BeregnNettoBarnetilsynResultatCore dummyNettoBarnetilsynResultatCore() {
    var bidragPeriodeResultatListe = new ArrayList<no.nav.bidrag.beregn.nettobarnetilsyn.dto.ResultatPeriodeCore>();
    bidragPeriodeResultatListe.add(new no.nav.bidrag.beregn.nettobarnetilsyn.dto.ResultatPeriodeCore(
        new PeriodeCore(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        singletonList(new no.nav.bidrag.beregn.nettobarnetilsyn.dto.ResultatBeregningCore(1, 100)),
        new no.nav.bidrag.beregn.nettobarnetilsyn.dto.ResultatGrunnlagCore(
            singletonList(new FaktiskUtgiftCore(LocalDate.parse("2010-01-01"), 1, 100d)), emptyList())));
    return new BeregnNettoBarnetilsynResultatCore(bidragPeriodeResultatListe, emptyList());
  }

  // Bygger opp BeregnNettoBarnetilsynResultatCore med avvik
  public static BeregnNettoBarnetilsynResultatCore dummyNettoBarnetilsynResultatCoreMedAvvik() {
    var avvikListe = new ArrayList<AvvikCore>();
    avvikListe.add(new AvvikCore("beregnDatoFra kan ikke være null", "NULL_VERDI_I_DATO"));
    avvikListe.add(new AvvikCore(
        "periodeDatoTil må være etter periodeDatoFra i faktiskUtgiftPeriodeListe: periodeDatoFra=2018-04-01, periodeDatoTil=2018-03-01",
        "DATO_FRA_ETTER_DATO_TIL"));
    return new BeregnNettoBarnetilsynResultatCore(emptyList(), avvikListe);
  }

  // Bygger opp BeregnUnderholdskostnadResultat
  public static BeregnUnderholdskostnadResultat dummyUnderholdskostnadResultat() {
    var bidragPeriodeResultatListe = new ArrayList<ResultatPeriodeUnderholdskostnad>();
    bidragPeriodeResultatListe.add(new ResultatPeriodeUnderholdskostnad(new Periode(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        new ResultatBeregningUnderholdskostnad(100d),
        new ResultatGrunnlagUnderholdskostnad(9, "DO", "64", 1000d, 1000d,
            singletonList(new Sjablon(SjablonTallNavn.ORDINAER_BARNETRYGD_BELOP.getNavn(), emptyList(),
                singletonList(new SjablonInnhold(SjablonInnholdNavn.SJABLON_VERDI.getNavn(), 1054d)))))));
    return new BeregnUnderholdskostnadResultat(bidragPeriodeResultatListe);
  }

  // Bygger opp BeregnUnderholdskostnadResultatCore
  public static BeregnUnderholdskostnadResultatCore dummyUnderholdskostnadResultatCore() {
    var bidragPeriodeResultatListe = new ArrayList<ResultatPeriodeCore>();
    bidragPeriodeResultatListe.add(new ResultatPeriodeCore(new PeriodeCore(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        new ResultatBeregningCore(100d),
        new ResultatGrunnlagCore(9, "DO", "64", 1000d, 1000d,
            singletonList(new SjablonCore(SjablonTallNavn.ORDINAER_BARNETRYGD_BELOP.getNavn(), emptyList(),
                singletonList(new SjablonInnholdCore(SjablonInnholdNavn.SJABLON_VERDI.getNavn(), 1054d)))))));
    return new BeregnUnderholdskostnadResultatCore(bidragPeriodeResultatListe, emptyList());
  }

  // Bygger opp BeregnUnderholdskostnadResultatCore med avvik
  public static BeregnUnderholdskostnadResultatCore dummyUnderholdskostnadResultatCoreMedAvvik() {
    var avvikListe = new ArrayList<AvvikCore>();
    avvikListe.add(new AvvikCore("beregnDatoFra kan ikke være null", "NULL_VERDI_I_DATO"));
    avvikListe.add(new AvvikCore(
        "periodeDatoTil må være etter periodeDatoFra i inntektPeriodeListe: periodeDatoFra=2018-04-01, periodeDatoTil=2018-03-01",
        "DATO_FRA_ETTER_DATO_TIL"));
    return new BeregnUnderholdskostnadResultatCore(emptyList(), avvikListe);
  }

  // Bygger opp BeregnSamvaersfradragResultat
  public static BeregnSamvaersfradragResultat dummySamvaersfradragResultat() {
    var bidragPeriodeResultatListe = new ArrayList<ResultatPeriodeSamvaersfradrag>();
    bidragPeriodeResultatListe.add(new ResultatPeriodeSamvaersfradrag(new Periode(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        new ResultatBeregningSamvaersfradrag(100d),
        new ResultatGrunnlagSamvaersfradrag(9, "00", emptyList())));
    return new BeregnSamvaersfradragResultat(bidragPeriodeResultatListe);
  }

  // Bygger opp BeregnSamvaersfradragResultatCore
  public static BeregnSamvaersfradragResultatCore dummySamvaersfradragResultatCore() {
    var bidragPeriodeResultatListe = new ArrayList<no.nav.bidrag.beregn.samvaersfradrag.dto.ResultatPeriodeCore>();
    bidragPeriodeResultatListe.add(new no.nav.bidrag.beregn.samvaersfradrag.dto.ResultatPeriodeCore(
        new PeriodeCore(LocalDate.parse("2017-01-01"), LocalDate.parse("2019-01-01")),
        new no.nav.bidrag.beregn.samvaersfradrag.dto.ResultatBeregningCore(100d),
        new no.nav.bidrag.beregn.samvaersfradrag.dto.ResultatGrunnlagCore(9, "00", emptyList())));
    return new BeregnSamvaersfradragResultatCore(bidragPeriodeResultatListe, emptyList());
  }

  // Bygger opp BeregnSamvaersfradragResultatCore med avvik
  public static BeregnSamvaersfradragResultatCore dummySamvaersfradragResultatCoreMedAvvik() {
    var avvikListe = new ArrayList<AvvikCore>();
    avvikListe.add(new AvvikCore("beregnDatoFra kan ikke være null", "NULL_VERDI_I_DATO"));
    avvikListe.add(new AvvikCore(
        "periodeDatoTil må være etter periodeDatoFra i inntektPeriodeListe: periodeDatoFra=2018-04-01, periodeDatoTil=2018-03-01",
        "DATO_FRA_ETTER_DATO_TIL"));
    return new BeregnSamvaersfradragResultatCore(emptyList(), avvikListe);
  }

  // Bygger opp liste av sjabloner av typen Sjablontall
  public static List<Sjablontall> dummySjablonSjablontallListe() {
    var sjablonSjablontallListe = new ArrayList<Sjablontall>();
    sjablonSjablontallListe.add(new Sjablontall("0001", LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(1054)));
    sjablonSjablontallListe.add(new Sjablontall("0003", LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(2775)));

    sjablonSjablontallListe.add(new Sjablontall("0001", LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(1074)));
    sjablonSjablontallListe.add(new Sjablontall("0003", LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(2795)));
    return sjablonSjablontallListe;
  }

  // Bygger opp liste av sjabloner av typen Forbruksutgifter
  public static List<Forbruksutgifter> dummySjablonForbruksutgifterListe() {
    var sjablonForbruksutgifterListe = new ArrayList<Forbruksutgifter>();
    sjablonForbruksutgifterListe.add(
        new Forbruksutgifter(5, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(3661)));
    sjablonForbruksutgifterListe.add(
        new Forbruksutgifter(5, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(3861)));
    sjablonForbruksutgifterListe.add(
        new Forbruksutgifter(10, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(5113)));
    sjablonForbruksutgifterListe.add(
        new Forbruksutgifter(10, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(5313)));
    return sjablonForbruksutgifterListe;
  }

  // Bygger opp liste av sjabloner av typen MaksTilsyn
  public static List<MaksTilsyn> dummySjablonMaksTilsynListe() {
    var sjablonMaksTilsynListe = new ArrayList<MaksTilsyn>();
    sjablonMaksTilsynListe.add(
        new MaksTilsyn(1, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(6214)));
    sjablonMaksTilsynListe.add(
        new MaksTilsyn(1, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(6314)));
    sjablonMaksTilsynListe.add(
        new MaksTilsyn(2, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(8109)));
    sjablonMaksTilsynListe.add(
        new MaksTilsyn(2, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(8209)));
    return sjablonMaksTilsynListe;
  }

  // Bygger opp liste av sjabloner av typen MaksFradrag
  public static List<MaksFradrag> dummySjablonMaksFradragListe() {
    var sjablonMaksFradragListe = new ArrayList<MaksFradrag>();
    sjablonMaksFradragListe.add(
        new MaksFradrag(1, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(1083)));
    sjablonMaksFradragListe.add(
        new MaksFradrag(1, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(1183)));
    sjablonMaksFradragListe.add(
        new MaksFradrag(2, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), BigDecimal.valueOf(3333)));
    sjablonMaksFradragListe.add(
        new MaksFradrag(2, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), BigDecimal.valueOf(3433)));
    return sjablonMaksFradragListe;
  }

  // Bygger opp liste av sjabloner av typen Samvaersfradrag
  public static List<Samvaersfradrag> dummySjablonSamvaersfradragListe() {
    var sjablonSamvaersfradragListe = new ArrayList<Samvaersfradrag>();
    sjablonSamvaersfradragListe.add(
        new Samvaersfradrag("00", 99, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), 1, 1, BigDecimal.valueOf(0)));
    sjablonSamvaersfradragListe.add(
        new Samvaersfradrag("00", 99, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), 1, 1, BigDecimal.valueOf(1000)));
    sjablonSamvaersfradragListe.add(
        new Samvaersfradrag("01", 5, LocalDate.parse("2017-07-01"), LocalDate.parse("2018-07-01"), 0, 8, BigDecimal.valueOf(727)));
    sjablonSamvaersfradragListe.add(
        new Samvaersfradrag("01", 5, LocalDate.parse("2018-07-01"), LocalDate.parse("2019-07-01"), 0, 8, BigDecimal.valueOf(827)));
    return sjablonSamvaersfradragListe;
  }
}
