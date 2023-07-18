package com.theZ.dotoring.enums;

import java.util.Arrays;
import java.util.List;

public enum Major {
    가정교육과, 간호학과, 건축_도시설계전공, 건축공학전공, 건축디자인학과, 건축학부, 경영학부, 경제학부, 고분자융합소재공학부, 교육학과, 국악학과_가야금, 국악학과_거문고, 국악학과_국악성악전공, 국악학과_국악작곡이론전공, 국악학과_대금, 국악학과_아쟁, 국악학과_타악, 국악학과_피리, 국악학과_해금, 국어교육과, 국어국문학과, 국제학부_영어학전공, 국제학부_일본학전공, 국제학부_중국학전공, 글로벌비즈니스학부, 기계공학부, 기계설계공학과, 기계시스템공학과, 기관시스템공학과, 냉동공조공학과, 농생명화학과, 농업경제학과, 독일언어문학과, 동물자원학부, 디자인학과, 멀티미디어전공, 메카트로닉스공학과, 문헌정보학과, 문화관광경영학과, 문화인류고고학과, 문화콘텐츠학부, 물류교통학과, 물리교육과, 물리학과, 미술학과_공예전공, 미술학과_서양화전공, 미술학과_이론전공, 미술학과_조소전공, 미술학과_한국화전공, 바이오에너지공학과, 분자생명공학과, 불어불문학과, 빅데이터융합학과, 사학과, 사회학과, 산림자원학과, 산업공학과, 산업기술융합공학과_야간, 생명과학기술학부, 생물공학과, 생물교육과, 생물학과, 생활복지학과, 석유화학소재공학과, 소프트웨어공학과, 수산생명의학과, 수의예과, 수학과, 수학교육과, 스마트수산자원관리학과, 식품공학과, 식품영양과학부, 신문방송학과, 신소재공학부, 심리학과, 약학부, 양식생물학과, 에너지자원공학과, 역사교육과, 영어교육과, 영어영문학과, 원예생명공학과, 유아교육과, 윤리교육과, 융합바이오시스템기계공학과, 융합생명공학과, 음악교육과, 음악학과_바순, 음악학과_바이올린, 음악학과_비올라, 음악학과성악전공, 음악학과_오보에, 음악학과_작곡전공, 음악학과_첼로, 음악학과_콘트라베이스, 음악학과_클라리넷또는색소폰, 음악학과_타악기, 음악학과_트럼펫, 음악학과_트롬본또는튜바, 음악학과_플루트, 음악학과_피아노전공, 음악학과_호른, 응용생물학과, 응용식물학과, 의류학과, 의예과, 인공지능학부, 일어일문학과, 임산공학과, 자율전공학부_1년, 자율전공학부_4년, 전기공학과, 전기및반도체공학전공, 전기컴퓨터공학부, 전자공학과, 전자상거래전공, 전자통신공학과, 정치외교학과, 조경학과, 조선해양공학과, 중어중문학과, 지구과학교육과, 지구환경과학부, 지능형모빌리티융합학과, 지능형모빌리티융합전공, 지리교육과, 지리학과, 지역_바이오시스템공학과, 창의융합학부, 철학과, 체육교육과, 치의학과_학석사통합과정, 컴퓨터공학전공, 컴퓨터정보통신공학과, 토목공학과, 통계학과, 특수교육학부, 해양경찰학과, 해양바이오식품학과, 해양생산관리학과, 해양융합과학과, 행정학과, 헬스케어메디컬공학부, 화공생명공학과, 화학공학부, 화학과, 화학교육과, 환경시스템공학과, 환경에너지공학과;

    public static Major getMajor(String major){
        Major findMajor = Arrays.stream(Major.values()).filter(m -> m.toString().equals(major)).findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학과입니다."));
        return findMajor;
    }

    public static List<Major> getMajors(){
        return Arrays.stream(Major.values()).toList();
    }

}