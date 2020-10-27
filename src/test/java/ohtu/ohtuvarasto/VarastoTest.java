package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto1;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto1 = new Varasto(-10);
        varasto2 = new Varasto(12,7.0);
        varasto3 = new Varasto(-12,7.0);
        varasto4 = new Varasto(12,17.0);
        varasto5 = new Varasto(12,-5.0);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiNegatiivistaTilavuutta() {
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2AloitussaldoOikein () {
        assertEquals(7.0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2TilavuusOikein () {
        assertEquals(12, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2SaldoEiYliTilavuuden () {
        assertEquals(12, varasto4.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2EiNegatiivistaSaldoa () {
        assertEquals(0, varasto5.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2EiNegatiivistaTilavuutta () {
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiMeneYli() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiNegatiivinen() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-2);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEiOtaLiikaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(12);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivinenEiMuuta() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}