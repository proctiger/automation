package uol.bt.commons.test.profile;

import java.util.HashMap;
import java.util.Map;

public class TraitMapper {

    @SuppressWarnings("serial")
    public static final Map<ProfileParameter, Map<String, Trait>> profileTraits = new HashMap<ProfileParameter, Map<String,Trait>>() {{
        put(ProfileParameter.jobFunction, new HashMap<String, Trait>(){{
            put("1", new Trait("33", "Ramo de atua��o em Servi�os de Consultoria e Neg�cios"));
            put("2", new Trait("34", "Ramo de atua��o em Servi�os Financeiros"));
            put("3", new Trait("35", "Ramo de atua��o em Seguros"));
            put("4", new Trait("36", "Ramo de atua��o em Inform�tica"));
            put("5", new Trait("37", "Ramo de atua��o em Educa��o/ Pesquisa"));
            put("6", new Trait("38", "Ramo de atua��o em Engenharia"));
            put("7", new Trait("39", "Ramo de atua��o em Ind�stria"));
            put("8", new Trait("40", "Ramo de atua��o em Servi�os de Sa�de"));
            put("9", new Trait("41", "Ramo de atua��o em Entretenimento"));
            put("10", new Trait("42", "Ramo de atua��o em Turismo"));
            put("11", new Trait("43", "Ramo de atua��o em Com�rcio/Varejo"));
            put("12", new Trait("44", "Ramo de atua��o em Agropecu�ria"));
            put("13", new Trait("45", "Ramo de atua��o em Organiza��o sem fins lucrativos"));
            put("14", new Trait("46", "Ramo de atua��o em Servi�os P�blicos"));
            put("15", new Trait("47", "Ramo de atua��o em Servi�os de Advocacia"));
            put("16", new Trait("48", "Ramo de atua��o em Comunica��o"));
            put("18", new Trait("49", "Ramo de atua��o em Arquitetura"));
            put("19", new Trait("50", "Ramo de atua��o em Artes"));
            put("20", new Trait("51", "Ramo de atua��o em Esporte"));
            put("21", new Trait("52", "Ramo de atua��o em Internet"));
            put("22", new Trait("53", "Ramo de atua��o em Marketing"));
            put("23", new Trait("54", "Ramo de atua��o em Recursos Humanos"));
        }});
        put(ProfileParameter.interest, new HashMap<String, Trait>(){{
            put("1", new Trait("55", "Interesse em Animais"));
            put("2", new Trait("56", "Interesse em Arte e cultura"));
            put("3", new Trait("57", "Interesse em Autom�veis"));
            put("4", new Trait("58", "Interesse em Bebidas e vinhos"));
            put("5", new Trait("59", "Interesse em Casa e jardim"));
            put("6", new Trait("60", "Interesse em Cinema e v�deo"));
            put("7", new Trait("61", "Interesse em Cole��es e miniaturas"));
            put("8", new Trait("62", "Interesse em Dinheiro"));
            put("9", new Trait("63", "Interesse em Esportes"));
            put("10", new Trait("64", "Interesse em Esportes de aventura"));
            put("11", new Trait("65", "Interesse em Games e brinquedos"));
            put("12", new Trait("66", "Interesse em Gastronomia"));
            put("13", new Trait("67", "Interesse em Inform�tica e Internet"));
            put("14", new Trait("68", "Interesse em Livros"));
            put("15", new Trait("69", "Interesse em Moda"));
            put("16", new Trait("70", "Interesse em M�sica"));
            put("17", new Trait("71", "Interesse em Papelaria e escrit�rio"));
            put("18", new Trait("72", "Interesse em Pol�tica"));
            put("19", new Trait("73", "Interesse em Sa�de e beleza"));
            put("20", new Trait("74", "Interesse em Sexo"));
            put("21", new Trait("75", "Interesse em Tabacaria"));
            put("22", new Trait("76", "Interesse em Viagens"));
        }});
        put(ProfileParameter.jobIndustry, new HashMap<String, Trait>(){{
            put("1", new Trait("77", "Profiss�o Estudante"));
            put("2", new Trait("78", "Profiss�o Profissional Liberal/ Empres�rio"));
            put("3", new Trait("79", "Profiss�o Executivo/Administrador"));
            put("4", new Trait("80", "Profiss�o Educador/Acad�mico"));
            put("5", new Trait("81", "Profiss�o Atendimento ao cliente"));
            put("6", new Trait("82", "Profiss�o Servi�os administrativos"));
            put("7", new Trait("83", "Profiss�o Servi�os Comerciais"));
            put("8", new Trait("84", "Profiss�o Planejamento/ Execu��o"));
            put("9", new Trait("85", "Profiss�o Dono(a) de Casa"));
            put("10", new Trait("86", "Profiss�o Procuro emprego"));
            put("11", new Trait("87", "Profiss�o Aposentado"));
            put("13", new Trait("88", "Profiss�o Militar"));
            put("14", new Trait("89", "Profiss�o Servidor P�blico"));
            put("15", new Trait("90", "Profiss�o Desocupado"));
        }});
        put(ProfileParameter.education, new HashMap<String, Trait>(){{
            put("1", new Trait("91", "Forma��o 1o. Grau"));
            put("2", new Trait("92", "Forma��o 2o. Grau"));
            put("4", new Trait("93", "Forma��o P�s-Gradua��o"));
            put("3", new Trait("94", "Forma��o Superior"));
            put("5", new Trait("95", "Forma��o curso t�cnico"));
        }});
        put(ProfileParameter.maritalStatus, new HashMap<String, Trait>(){{
            put("1", new Trait("96", "Estado civil Casado(a)"));
            put("3", new Trait("97", "Estado civil Separado(a)"));
            put("2", new Trait("98", "Estado civil Solteiro(a)"));
            put("5", new Trait("99", "Estado civil Vive com companheiro(a)"));
            put("4", new Trait("100", "Estado civil Vi�vo(a)"));
        }});
        put(ProfileParameter.gender, new HashMap<String, Trait>(){{
            put("M", new Trait("101", "Sexo Masculino"));
            put("F", new Trait("102", "Sexo Feminino"));
        }});
        put(ProfileParameter.age, new HashMap<String, Trait>(87));
    }};

    static {
        final Map<String, Trait> ageMap = profileTraits.get(ProfileParameter.age);
        for (int age = 13; age <= 99; age++) {
            ageMap.put(String.valueOf(age), new Trait("1" + age, "Idade " + age + " anos"));
        }
    }

    @SuppressWarnings("serial")
    @Deprecated
    public static final Map<String, String> pageviewTraits = new HashMap<String, String>() {{
        put("201", "Autom�veis e ve�culos");
        put("202", "Viagens e turismo");
        put("203", "Mulher, moda e beleza");
        put("204", "Vestibulares");
        put("205", "Im�veis");
        put("206", "Sa�de, dieta e boa forma");
        put("207", "Celulares e telefonia");
        put("208", "Servi�os a PJ");
        put("209", "Inform�tica");
        put("210", "Celulares e telefonia - Inten��o de compra");
        put("211", "Inform�tica - Inten��o de compra");
        put("212", "Economia");
        put("213", "Educa��o");
        put("214", "Moda");
        put("215", "Beleza");
        put("216", "Eletr�nicos");
        put("217", "Empregos");
        put("218", "Entretenimento");
        put("219", "Esportes");
    }};
}
