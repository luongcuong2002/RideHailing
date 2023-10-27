package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R

enum class CountryCode(
    val countryFlag: Int,
    val countryName: String,
    val countryCode: String,
    val phoneCode: String,
    val minLength: Int = 3,
    val maxLength: Int = 20
) {
    AFGHANISTAN(R.drawable.flag_af, "Afghanistan", "AF", "+93"),
    ALBANIA(R.drawable.flag_al, "Albania", "AL", "+355"),
    ALGERIA(R.drawable.flag_dz, "Algeria", "DZ", "+213"),
    AMERICAN_SAMOA(R.drawable.flag_as, "American Samoa", "AS", "+1684"),
    ANDORRA(R.drawable.flag_ad, "Andorra", "AD", "+376"),
    ANGOLA(R.drawable.flag_ao, "Angola", "AO", "+244"),
    ANGUILLA(R.drawable.flag_ai, "Anguilla", "AI", "+1264"),
    ANTARCTICA(R.drawable.flag_aq, "Antarctica", "AQ", "+672"),
    ANTIGUA_AND_BARBUDA(R.drawable.flag_ag, "Antigua and Barbuda", "AG", "+1268"),
    ARGENTINA(R.drawable.flag_ar, "Argentina", "AR", "+54"),
    ARMENIA(R.drawable.flag_am, "Armenia", "AM", "+374"),
    ARUBA(R.drawable.flag_aw, "Aruba", "AW", "+297"),
    AUSTRALIA(R.drawable.flag_au, "Australia", "AU", "+61"),
    AUSTRIA(R.drawable.flag_at, "Austria", "AT", "+43"),
    AZERBAIJAN(R.drawable.flag_az, "Azerbaijan", "AZ", "+994"),
    BAHAMAS(R.drawable.flag_bs, "Bahamas", "BS", "+1242"),
    BAHRAIN(R.drawable.flag_bh, "Bahrain", "BH", "+973"),
    BANGLADESH(R.drawable.flag_bd, "Bangladesh", "BD", "+880"),
    BARBADOS(R.drawable.flag_bb, "Barbados", "BB", "+1246"),
    BELARUS(R.drawable.flag_by, "Belarus", "BY", "+375"),
    BELGIUM(R.drawable.flag_be, "Belgium", "BE", "+32"),
    BELIZE(R.drawable.flag_bz, "Belize", "BZ", "+501"),
    BENIN(R.drawable.flag_bj, "Benin", "BJ", "+229"),
    BERMUDA(R.drawable.flag_bm, "Bermuda", "BM", "+1441"),
    BHUTAN(R.drawable.flag_bt, "Bhutan", "BT", "+975"),
    BOLIVIA(R.drawable.flag_bo, "Bolivia", "BO", "+591"),
    BOSNIA_AND_HERZEGOVINA(R.drawable.flag_ba, "Bosnia and Herzegovina", "BA", "+387"),
    BOTSWANA(R.drawable.flag_bw, "Botswana", "BW", "+267"),
    BRAZIL(R.drawable.flag_br, "Brazil", "BR", "+55"),
    BRITISH_INDIAN_OCEAN_TERRITORY(R.drawable.flag_io, "British Indian Ocean Territory", "IO", "+246"),
    BRITISH_VIRGIN_ISLANDS(R.drawable.flag_vg, "British Virgin Islands", "VG", "+1284"),
    BRUNEI(R.drawable.flag_bn, "Brunei", "BN", "+673"),
    BULGARIA(R.drawable.flag_bg, "Bulgaria", "BG", "+359"),
    BURKINA_FASO(R.drawable.flag_bf, "Burkina Faso", "BF", "+226"),
    BURUNDI(R.drawable.flag_bi, "Burundi", "BI", "+257"),
    CAMBODIA(R.drawable.flag_kh, "Cambodia", "KH", "+855"),
    CAMEROON(R.drawable.flag_cm, "Cameroon", "CM", "+237"),
    CANADA(R.drawable.flag_ca, "Canada", "CA", "+1"),
    CAPE_VERDE(R.drawable.flag_cv, "Cape Verde", "CV", "+238"),
    CAYMAN_ISLANDS(R.drawable.flag_ky, "Cayman Islands", "KY", "+1345"),
    CENTRAL_AFRICAN_REPUBLIC(R.drawable.flag_cf, "Central African Republic", "CF", "+236"),
    CHAD(R.drawable.flag_td, "Chad", "TD", "+235"),
    CHILE(R.drawable.flag_cl, "Chile", "CL", "+56"),
    CHINA(R.drawable.flag_cn, "China", "CN", "+86"),
    CHRISTMAS_ISLAND(R.drawable.flag_cx, "Christmas Island", "CX", "+61"),
    COCOS_ISLANDS(R.drawable.flag_cc, "Cocos Islands", "CC", "+61"),
    COLOMBIA(R.drawable.flag_co, "Colombia", "CO", "+57"),
    COMOROS(R.drawable.flag_km, "Comoros", "KM", "+269"),
    COOK_ISLANDS(R.drawable.flag_ck, "Cook Islands", "CK", "+682"),
    COSTA_RICA(R.drawable.flag_cr, "Costa Rica", "CR", "+506"),
    CROATIA(R.drawable.flag_hr, "Croatia", "HR", "+385"),
    CUBA(R.drawable.flag_cu, "Cuba", "CU", "+53"),
    CURACAO(R.drawable.flag_cw, "Curacao", "CW", "+599"),
    CYPRUS(R.drawable.flag_cy, "Cyprus", "CY", "+357"),
    CZECH_REPUBLIC(R.drawable.flag_cz, "Czech Republic", "CZ", "+420"),
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO(R.drawable.flag_cd, "Democratic Republic of the Congo", "CD", "+243"),
    DENMARK(R.drawable.flag_dk, "Denmark", "DK", "+45"),
    DJIBOUTI(R.drawable.flag_dj, "Djibouti", "DJ", "+253"),
    DOMINICA(R.drawable.flag_dm, "Dominica", "DM", "+1767"),
    DOMINICAN_REPUBLIC(R.drawable.flag_do, "Dominican Republic", "DO", "+1809"),
    EAST_TIMOR(R.drawable.flag_tl, "East Timor", "TL", "+670"),
    ECUADOR(R.drawable.flag_ec, "Ecuador", "EC", "+593"),
    EGYPT(R.drawable.flag_eg, "Egypt", "EG", "+20"),
    EL_SALVADOR(R.drawable.flag_sv, "El Salvador", "SV", "+503"),
    EQUATORIAL_GUINEA(R.drawable.flag_gq, "Equatorial Guinea", "GQ", "+240"),
    ERITREA(R.drawable.flag_er, "Eritrea", "ER", "+291"),
    ESTONIA(R.drawable.flag_ee, "Estonia", "EE", "+372"),
    ETHIOPIA(R.drawable.flag_et, "Ethiopia", "ET", "+251"),
    FALKLAND_ISLANDS(R.drawable.flag_fk, "Falkland Islands", "FK", "+500"),
    FAROE_ISLANDS(R.drawable.flag_fo, "Faroe Islands", "FO", "+298"),
    FIJI(R.drawable.flag_fj, "Fiji", "FJ", "+679"),
    FINLAND(R.drawable.flag_fi, "Finland", "FI", "+358"),
    FRANCE(R.drawable.flag_fr, "France", "FR", "+33"),
    FRENCH_POLYNESIA(R.drawable.flag_pf, "French Polynesia", "PF", "+689"),
    GABON(R.drawable.flag_ga, "Gabon", "GA", "+241"),
    GAMBIA(R.drawable.flag_gm, "Gambia", "GM", "+220"),
    GEORGIA(R.drawable.flag_ge, "Georgia", "GE", "+995"),
    GERMANY(R.drawable.flag_de, "Germany", "DE", "+49"),
    GHANA(R.drawable.flag_gh, "Ghana", "GH", "+233"),
    GIBRALTAR(R.drawable.flag_gi, "Gibraltar", "GI", "+350"),
    GREECE(R.drawable.flag_gr, "Greece", "GR", "+30"),
    GREENLAND(R.drawable.flag_gl, "Greenland", "GL", "+299"),
    GRENADA(R.drawable.flag_gd, "Grenada", "GD", "+1473"),
    GUAM(R.drawable.flag_gu, "Guam", "GU", "+1671"),
    GUATEMALA(R.drawable.flag_gt, "Guatemala", "GT", "+502"),
    GUERNSEY(R.drawable.flag_gg, "Guernsey", "GG", "+44"),
    GUINEA(R.drawable.flag_gn, "Guinea", "GN", "+224"),
    GUINEA_BISSAU(R.drawable.flag_gw, "Guinea-Bissau", "GW", "+245"),
    GUYANA(R.drawable.flag_gy, "Guyana", "GY", "+592"),
    HAITI(R.drawable.flag_ht, "Haiti", "HT", "+509"),
    HONDURAS(R.drawable.flag_hn, "Honduras", "HN", "+504"),
    HONG_KONG(R.drawable.flag_hk, "Hong Kong", "HK", "+852"),
    HUNGARY(R.drawable.flag_hu, "Hungary", "HU", "+36"),
    ICELAND(R.drawable.flag_is, "Iceland", "IS", "+354"),
    INDIA(R.drawable.flag_in, "India", "IN", "+91"),
    INDONESIA(R.drawable.flag_id, "Indonesia", "ID", "+62"),
    IRAN(R.drawable.flag_ir, "Iran", "IR", "+98"),
    IRAQ(R.drawable.flag_iq, "Iraq", "IQ", "+964"),
    IRELAND(R.drawable.flag_ie, "Ireland", "IE", "+353"),
    ISLE_OF_MAN(R.drawable.flag_im, "Isle of Man", "IM", "+44"),
    ISRAEL(R.drawable.flag_il, "Israel", "IL", "+972"),
    ITALY(R.drawable.flag_it, "Italy", "IT", "+39"),
    IVORY_COAST(R.drawable.flag_ci, "Ivory Coast", "CI", "+225"),
    JAMAICA(R.drawable.flag_jm, "Jamaica", "JM", "+1876"),
    JAPAN(R.drawable.flag_jp, "Japan", "JP", "+81"),
    JERSEY(R.drawable.flag_je, "Jersey", "JE", "+44"),
    JORDAN(R.drawable.flag_jo, "Jordan", "JO", "+962"),
    KAZAKHSTAN(R.drawable.flag_kz, "Kazakhstan", "KZ", "+7"),
    KENYA(R.drawable.flag_ke, "Kenya", "KE", "+254"),
    KIRIBATI(R.drawable.flag_ki, "Kiribati", "KI", "+686"),
    KOSOVO(R.drawable.flag_xk, "Kosovo", "XK", "+383"),
    KUWAIT(R.drawable.flag_kw, "Kuwait", "KW", "+965"),
    KYRGYZSTAN(R.drawable.flag_kg, "Kyrgyzstan", "KG", "+996"),
    LAOS(R.drawable.flag_la, "Laos", "LA", "+856"),
    LATVIA(R.drawable.flag_lv, "Latvia", "LV", "+371"),
    LEBANON(R.drawable.flag_lb, "Lebanon", "LB", "+961"),
    LESOTHO(R.drawable.flag_ls, "Lesotho", "LS", "+266"),
    LIBERIA(R.drawable.flag_lr, "Liberia", "LR", "+231"),
    LIBYA(R.drawable.flag_ly, "Libya", "LY", "+218"),
    LIECHTENSTEIN(R.drawable.flag_li, "Liechtenstein", "LI", "+423"),
    LITHUANIA(R.drawable.flag_lt, "Lithuania", "LT", "+370"),
    LUXEMBOURG(R.drawable.flag_lu, "Luxembourg", "LU", "+352"),
    MACAU(R.drawable.flag_mo, "Macau", "MO", "+853"),
    MACEDONIA(R.drawable.flag_mk, "Macedonia", "MK", "+389"),
    MADAGASCAR(R.drawable.flag_mg, "Madagascar", "MG", "+261"),
    MALAWI(R.drawable.flag_mw, "Malawi", "MW", "+265"),
    MALAYSIA(R.drawable.flag_my, "Malaysia", "MY", "+60"),
    MALDIVES(R.drawable.flag_mv, "Maldives", "MV", "+960"),
    MALI(R.drawable.flag_ml, "Mali", "ML", "+223"),
    MALTA(R.drawable.flag_mt, "Malta", "MT", "+356"),
    MARSHALL_ISLANDS(R.drawable.flag_mh, "Marshall Islands", "MH", "+692"),
    MAURITANIA(R.drawable.flag_mr, "Mauritania", "MR", "+222"),
    MAURITIUS(R.drawable.flag_mu, "Mauritius", "MU", "+230"),
    MAYOTTE(R.drawable.flag_yt, "Mayotte", "YT", "+262"),
    MEXICO(R.drawable.flag_mx, "Mexico", "MX", "+52"),
    MICRONESIA(R.drawable.flag_fm, "Micronesia", "FM", "+691"),
    MOLDOVA(R.drawable.flag_md, "Moldova", "MD", "+373"),
    MONACO(R.drawable.flag_mc, "Monaco", "MC", "+377"),
    MONGOLIA(R.drawable.flag_mn, "Mongolia", "MN", "+976"),
    MONTENEGRO(R.drawable.flag_me, "Montenegro", "ME", "+382"),
    MONTSERRAT(R.drawable.flag_ms, "Montserrat", "MS", "+1664"),
    MOROCCO(R.drawable.flag_ma, "Morocco", "MA", "+212"),
    MOZAMBIQUE(R.drawable.flag_mz, "Mozambique", "MZ", "+258"),
    MYANMAR(R.drawable.flag_mm, "Myanmar", "MM", "+95"),
    NAMIBIA(R.drawable.flag_na, "Namibia", "NA", "+264"),
    NAURU(R.drawable.flag_nr, "Nauru", "NR", "+674"),
    NEPAL(R.drawable.flag_np, "Nepal", "NP", "+977"),
    NETHERLANDS(R.drawable.flag_nl, "Netherlands", "NL", "+31"),
    NEW_CALEDONIA(R.drawable.flag_nc, "New Caledonia", "NC", "+687"),
    NEW_ZEALAND(R.drawable.flag_nz, "New Zealand", "NZ", "+64"),
    NICARAGUA(R.drawable.flag_ni, "Nicaragua", "NI", "+505"),
    NIGER(R.drawable.flag_ne, "Niger", "NE", "+227"),
    NIGERIA(R.drawable.flag_ng, "Nigeria", "NG", "+234"),
    NIUE(R.drawable.flag_nu, "Niue", "NU", "+683"),
    NORTH_KOREA(R.drawable.flag_kp, "North Korea", "KP", "+850"),
    NORTHERN_MARIANA_ISLANDS(R.drawable.flag_mp, "Northern Mariana Islands", "MP", "+1670"),
    NORWAY(R.drawable.flag_no, "Norway", "NO", "+47"),
    OMAN(R.drawable.flag_om, "Oman", "OM", "+968"),
    PAKISTAN(R.drawable.flag_pk, "Pakistan", "PK", "+92"),
    PALAU(R.drawable.flag_pw, "Palau", "PW", "+680"),
    PALESTINE(R.drawable.flag_ps, "Palestine", "PS", "+970"),
    PANAMA(R.drawable.flag_pa, "Panama", "PA", "+507"),
    PAPUA_NEW_GUINEA(R.drawable.flag_pg, "Papua New Guinea", "PG", "+675"),
    PARAGUAY(R.drawable.flag_py, "Paraguay", "PY", "+595"),
    PERU(R.drawable.flag_pe, "Peru", "PE", "+51"),
    PHILIPPINES(R.drawable.flag_ph, "Philippines", "PH", "+63"),
    PITCAIRN(R.drawable.flag_pn, "Pitcairn", "PN", "+64"),
    POLAND(R.drawable.flag_pl, "Poland", "PL", "+48"),
    PORTUGAL(R.drawable.flag_pt, "Portugal", "PT", "+351"),
    PUERTO_RICO(R.drawable.flag_pr, "Puerto Rico", "PR", "+1787"),
    QATAR(R.drawable.flag_qa, "Qatar", "QA", "+974"),
    REPUBLIC_OF_THE_CONGO(R.drawable.flag_cg, "Republic of the Congo", "CG", "+242"),
    REUNION(R.drawable.flag_re, "Reunion", "RE", "+262"),
    ROMANIA(R.drawable.flag_ro, "Romania", "RO", "+40"),
    RUSSIA(R.drawable.flag_ru, "Russia", "RU", "+7"),
    RWANDA(R.drawable.flag_rw, "Rwanda", "RW", "+250"),
    SAINT_BARTHELEMY(R.drawable.flag_bl, "Saint Barthelemy", "BL", "+590"),
    SAINT_HELENA(R.drawable.flag_sh, "Saint Helena", "SH", "+290"),
    SAINT_KITTS_AND_NEVIS(R.drawable.flag_kn, "Saint Kitts and Nevis", "KN", "+1869"),
    SAINT_LUCIA(R.drawable.flag_lc, "Saint Lucia", "LC", "+1758"),
    SAINT_MARTIN(R.drawable.flag_mf, "Saint Martin", "MF", "+590"),
    SAINT_PIERRE_AND_MIQUELON(R.drawable.flag_pm, "Saint Pierre and Miquelon", "PM", "+508"),
    SAINT_VINCENT_AND_THE_GRENADINES(R.drawable.flag_vc, "Saint Vincent and the Grenadines", "VC", "+1784"),
    SAMOA(R.drawable.flag_ws, "Samoa", "WS", "+685"),
    SAN_MARINO(R.drawable.flag_sm, "San Marino", "SM", "+378"),
    SAO_TOME_AND_PRINCIPE(R.drawable.flag_st, "Sao Tome and Principe", "ST", "+239"),
    SAUDI_ARABIA(R.drawable.flag_sa, "Saudi Arabia", "SA", "+966"),
    SENEGAL(R.drawable.flag_sn, "Senegal", "SN", "+221"),
    SERBIA(R.drawable.flag_rs, "Serbia", "RS", "+381"),
    SEYCHELLES(R.drawable.flag_sc, "Seychelles", "SC", "+248"),
    SIERRA_LEONE(R.drawable.flag_sl, "Sierra Leone", "SL", "+232"),
    SINGAPORE(R.drawable.flag_sg, "Singapore", "SG", "+65"),
    SINT_MAARTEN(R.drawable.flag_sx, "Sint Maarten", "SX", "+1721"),
    SLOVAKIA(R.drawable.flag_sk, "Slovakia", "SK", "+421"),
    SLOVENIA(R.drawable.flag_si, "Slovenia", "SI", "+386"),
    SOLOMON_ISLANDS(R.drawable.flag_sb, "Solomon Islands", "SB", "+677"),
    SOMALIA(R.drawable.flag_so, "Somalia", "SO", "+252"),
    SOUTH_AFRICA(R.drawable.flag_za, "South Africa", "ZA", "+27"),
    SOUTH_KOREA(R.drawable.flag_kr, "South Korea", "KR", "+82"),
    SOUTH_SUDAN(R.drawable.flag_ss, "South Sudan", "SS", "+211"),
    SPAIN(R.drawable.flag_es, "Spain", "ES", "+34"),
    SRI_LANKA(R.drawable.flag_lk, "Sri Lanka", "LK", "+94"),
    SUDAN(R.drawable.flag_sd, "Sudan", "SD", "+249"),
    SURINAME(R.drawable.flag_sr, "Suriname", "SR", "+597"),
    SVALBARD_AND_JAN_MAYEN(R.drawable.flag_sj, "Svalbard and Jan Mayen", "SJ", "+47"),
    SWAZILAND(R.drawable.flag_sz, "Swaziland", "SZ", "+268"),
    SWEDEN(R.drawable.flag_se, "Sweden", "SE", "+46"),
    SWITZERLAND(R.drawable.flag_ch, "Switzerland", "CH", "+41"),
    SYRIA(R.drawable.flag_sy, "Syria", "SY", "+963"),
    TAIWAN(R.drawable.flag_tw, "Taiwan", "TW", "+886"),
    TAJIKISTAN(R.drawable.flag_tj, "Tajikistan", "TJ", "+992"),
    TANZANIA(R.drawable.flag_tz, "Tanzania", "TZ", "+255"),
    THAILAND(R.drawable.flag_th, "Thailand", "TH", "+66"),
    TOGO(R.drawable.flag_tg, "Togo", "TG", "+228"),
    TOKELAU(R.drawable.flag_tk, "Tokelau", "TK", "+690"),
    TONGA(R.drawable.flag_to, "Tonga", "TO", "+676"),
    TRINIDAD_AND_TOBAGO(R.drawable.flag_tt, "Trinidad and Tobago", "TT", "+1868"),
    TUNISIA(R.drawable.flag_tn, "Tunisia", "TN", "+216"),
    TURKEY(R.drawable.flag_tr, "Turkey", "TR", "+90"),
    TURKMENISTAN(R.drawable.flag_tm, "Turkmenistan", "TM", "+993"),
    TURKS_AND_CAICOS_ISLANDS(R.drawable.flag_tc, "Turks and Caicos Islands", "TC", "+1649"),
    TUVALU(R.drawable.flag_tv, "Tuvalu", "TV", "+688"),
    US_VIRGIN_ISLANDS(R.drawable.flag_vi, "U.S. Virgin Islands", "VI", "+1340"),
    UGANDA(R.drawable.flag_ug, "Uganda", "UG", "+256"),
    UKRAINE(R.drawable.flag_ua, "Ukraine", "UA", "+380"),
    UNITED_ARAB_EMIRATES(R.drawable.flag_ae, "United Arab Emirates", "AE", "+971"),
    UNITED_KINGDOM(R.drawable.flag_gb, "United Kingdom", "GB", "+44"),
    UNITED_STATES(R.drawable.flag_us, "United States", "US", "+1"),
    URUGUAY(R.drawable.flag_uy, "Uruguay", "UY", "+598"),
    UZBEKISTAN(R.drawable.flag_uz, "Uzbekistan", "UZ", "+998"),
    VANUATU(R.drawable.flag_vu, "Vanuatu", "VU", "+678"),
    VATICAN(R.drawable.flag_va, "Vatican", "VA", "+379"),
    VENEZUELA(R.drawable.flag_ve, "Venezuela", "VE", "+58"),
    VIETNAM(R.drawable.flag_vn, "Vietnam", "VN", "+84"),
    WALLIS_AND_FUTUNA(R.drawable.flag_wf, "Wallis and Futuna", "WF", "+681"),
    WESTERN_SAHARA(R.drawable.flag_eh, "Western Sahara", "EH", "+212"),
    YEMEN(R.drawable.flag_ye, "Yemen", "YE", "+967"),
    ZAMBIA(R.drawable.flag_zm, "Zambia", "ZM", "+260"),
    ZIMBABWE(R.drawable.flag_zw, "Zimbabwe", "ZW", "+263"),
}