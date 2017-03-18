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
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
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

        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusOnAnnettuOikein() {
        double tilavuus = -2.3;
        Varasto varasto = new Varasto(tilavuus);
        
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisättäväMääräOnOikein() {
        varasto.lisaaVarastoon(-1.5);
        
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoTäynnäJosLisätäänYliKapasiteetin() {
        varasto.lisaaVarastoon(15.5);

        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josOtetaanNegatiivinenMääräEiOtetaMitään() {
        varasto.otaVarastosta(-5.5);

        assertEquals(0.0, varasto.otaVarastosta(-5.5), vertailuTarkkuus);
    }
    
    @Test
    public void annetaanMitäVoidaan() {
        varasto.lisaaVarastoon(7.8);
        varasto.otaVarastosta(8.5);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tulostusOnOikein() {
        varasto.lisaaVarastoon(7.8);

        assertEquals("saldo = 7.8, vielä tilaa 2.2", varasto.toString());
    }
    
    @Test
    public void luoVarastonSaldoineenOikein() {
        varasto = new Varasto(15.0, 8.0);

        assertEquals(15.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(8.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luoKelvottomanVarastoaJosVirheellinenTilavuus() {
        Varasto testiVarasto = new Varasto(-5.0, 0.0);

        assertEquals(0.0, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void luoTyhjänVarastonJosNegatiivinenAlkusaldo() {
        varasto = new Varasto(10.0, -4.0);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
}