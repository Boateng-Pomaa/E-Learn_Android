package com.example.e_learn.ui.login.ui.socialStudies

data class SocialData(val topic:String,val subtopic:List<String>){

    companion object {
        fun getData():List<SocialData >{
            return listOf(
                SocialData("ENVIRONMENT", listOf("Definition of Social Studies",
                    "Self Identity",
                    "Adolescent Reproductive Health",
                    "Our Culture and National Identity")),
            SocialData("GOVERNANCE, POLITICS AND STABILITY", listOf(
            "National Independence and Self-reliance",
            "Peace Building and Conflict Resolution")),
            SocialData("SOCIO-ECONOMIC DEVELOPMENT", listOf(
            "The Youth and National Development",
            "Developing national consciousness and unity",
            "Science and Technology"


            ))) }}}


data class SocialStudies(val topic:String,val subtopic:List<String>){

    companion object {
        fun getData():List<SocialStudies >{
            return listOf(
                SocialStudies("GOVERNANCE, POLITICS AND STABILITY", listOf("Definition of Social Studies",
                    "Leadership and Followership",
                    "Our Constitution, Democracy and Nation Building")),
            SocialStudies("GOVERNANCE, POLITICS AND STABILITY", listOf(
            "Leadership and Followership",
            "Our Constitution, Democracy and Nation Building")),
            SocialStudies("SOCIO-ECONOMIC DEVELOPMENT", listOf(
            "The Role of the individual in Community Development",
            "Promoting National Socio-Economic Development",
            "Sustainable Development"


            ))) }}}




data class SocialStudies3(val topic:String,val subtopic:List<String>){

    companion object {
        fun getData():List<SocialStudies3 >{
            return listOf(
                SocialStudies3("ENVIRONMENT", listOf("Definition of Social Studies",
                    "Our Physical Environment and Environmental Challenges",
                    "Education and Societal Change")),
            SocialStudies3("GOVERNANCE, POLITICS AND STABILITY", listOf(
            "Rights and Responsibilities of the Individual",
            "Ghana and the International Community")),
            SocialStudies3("SOCIO-ECONOMIC DEVELOPMENT", listOf(
            "Population Growth and Development",
            "The world of Work and Entrepreneurship",

            ))) }}}


