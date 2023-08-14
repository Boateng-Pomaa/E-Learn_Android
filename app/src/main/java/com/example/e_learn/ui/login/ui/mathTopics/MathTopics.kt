package com.example.e_learn.ui.login.ui.mathTopics


data class MathTopics(val topic:String,val subtopic:List<String>){

    companion object {
        fun getData():List<MathTopics>{
            return listOf(
                MathTopics("SELECT A TOPIC", listOf("a","b","c")),
                MathTopics("SETS AND OPERATIONS ON SETS", listOf("1Finding the number of subsets in a set with n elements",
                        "Properties of Set Operations- Commutativity Associativity Distributivity",
                "Description and identification of the regions of Venn diagrams using set operations",
            	"Three-set problems using Venn diagrams")),
                MathTopics("REAL NUMBER SYSTEM", listOf(
                    "Rational and irrational numbers",
                    "Real Numbers on the number line",
                "Comparing and ordering rational numbers",
            "Approximating and rounding off numbers",
            "Significant figures",
            "Recurring decimals",
            "Standard form",
            "Properties of operations Commutative property, Associative Property, Distributive Property",
            "Binary operations"

            )),
                MathTopics("ALGEBRAIC EXPRESSIONS", listOf(
                    "Algebraic expressions",
                    "Operations on algebraic expressions",
                "Binomial expressions",
            "Factorization",
            "Difference of two squares",
            "Operations on algebraic fractions with monomial denominators",
            "Zero or Undefined algebraic fractions"
            )),
                MathTopics("SURDS", listOf("Simplifying surds",
                "Addition, subtraction and multiplication of surds",
                "Rationalization of surds with monomial denominators"
                )),
                MathTopics("NUMBER BASES", listOf("Converting base ten numerals to numerals in other bases and vice versa",
                "Equations involving number bases", "Operations on numbers involving number bases other than base ten"
            )),
                MathTopics("RELATIONS AND FUNCTIONS", listOf(	"Types of relations",
                        "Functions",
                        "Mapping",
                        "Graphs of Linear Functions",
                        "Gradient of a straight line",
                        "Equation of a straight line",
                        "Magnitude of a line segment",
                        "Graphs of Quadratic functions"
            )),
                MathTopics("PLANE GEOMETRY I", listOf( "Angles at a point",
                    "Parallel lines Relationships between corresponding angles, vertically opposite angles, alternate angles and adjacent angles, supplementary angles",
                        "Exterior angle theorem",
                        "Special triangles Isosceles and equilateral triangles",
                        "Right–angled triangle",
                        "Quadrilaterals", "Polygons"
            )),
                MathTopics("FORMULARS, LINEAR EQUATIONS AND INEQUALITIES", listOf("Formula",
                        "Change of subject of an equation",
                        "Solution sets of linear equations in one variable",
                        "Word problems involving linear equations in one variable",
                        "Linear inequalities in one variable",
                        "Word problems involving linear inequalities in one variable"
            )),
                MathTopics("BEARINGS AND VECTORS IN A PLANE", listOf("Bearing of a point from another",
                "Distance-bearing form", "Reverse bearing", "Scalar and vector quantities",
                "Vector notation and representation", "Addition and subtraction of vectors",
                "Multiplying a vector by a scalar", "Column vectors", "Triangle law of vectors",
                "Equal and Parallel vectors", "Negative vectors", "Magnitude and direction of a vector"
            )),
                MathTopics("STATISTICS I", listOf("Frequency distribution tables",
                    "Data presented in tables", "Graphical representation of data", "Mean of a distribution"
            )),
                MathTopics("RIGID MOTION I", listOf("Translation by a vector",
                "Reflection in a line", "Characteristics of reflection"
                )),
                MathTopics("RATIO AND RATES", listOf("Ratio", "Scales", "Foreign exchange",
                    "Rates", "Travel Graphs", "Population Density"
            )),
                MathTopics("PERCENTAGES I", listOf("Comparison by percentages",
                        "Discount, Commission, Simple Interest",
                    "Hire Purchase"
            ))

            )
        }
    }
}

data class MathTopics2(val topic:String,val subtopic:List<String>){
    companion object{
        fun getData():List<MathTopics2>{
            return listOf(
                MathTopics2("SELECT A TOPIC", listOf("a","b","c")),
                MathTopics2("MODULAR ARITHMETIC", listOf("Calculation of a number for a given modulo",
                "Addition () and multiplication () tables in given modulo"
                )),
                MathTopics2("INDICES AND LOGARITHMS", listOf("Laws of indices",
                    "Solving equations involving indices",
                    "Relating indices to logarithms in base ten",
                    "Anti-logarithms of given numbers"
            )),
                MathTopics2("SIMULTANEOUS LINEAR EQUATION", listOf("Graphical method for solving linear equations in two variables",
                        "Elimination and substitution methods for solving linear equations in two variables",
                        "Solving word problems involving simultaneous linear equations in two variables"
                )),
                MathTopics2("PERCENTAGES II", listOf("Compound interest for a given period(Up to 4 years)",
                    "Depreciation",
                    "Financial Partnership",
                    "Interest(Profit) on capital",
                    "Banking","Income Tax",
                    "Value Added Tax(VAT)", "Household bills"
            )),
                MathTopics2("VARIATION", listOf("Direct variation",
                    "Solving problems involving direct variations",
                    "Indirect variations(inverse variations)",
                    "Solving problems involving joint variations",
                    "Partial variations"
            )),
                MathTopics2("STATISTICS II", listOf("Histogram", "Mean",
                        "Cumulative Frequency Curves(Ogive)",
                         "Standard deviation and Variance"
                )),
                MathTopics2("PROBABILITY", listOf("Sample Space of simple experiments",
                        "Sample Space of compound experiment",
                        "Probability of an event",
                        "Addition law for mutually exclusive events",
                        "Multiplication law for independent events"
            )),
                MathTopics2("QUADRATIC FUNCTIONS AND EQUATIONS", listOf("Solving quadratic equations by factorization",
                          "Graphical solution of quadratic equations",
                          "Minimum and maximum values and points of quadratic graphs",
                          "Minimum and maximum values and points of quadratic graphs",
                          "Solving linear and quadratic equations using graphs",
                          "Solving related quadratic equations",
                          "Increasing/Decreasing values of quadratic graphs",
                          "Positive/Negative values of quadratic graph"
            )),
                MathTopics2("MENSURATION I", listOf("Length of an arc",
                      "Perimeter of plane figures","Areas of sectors and segments",
                      "Areas of quadrilaterals"
            )),
                MathTopics2("PLANE GEOMETRY II (CIRCLES)", listOf("The Circle as a Locus",
                    "Circle Theorems",
                    "Perpendicularity of Tangent and Radius of a Circle",
                    "Angle between Tangent and a Chord",
                    "Tangents from an External Point"
            )),
                MathTopics2("TRIGONOMETRY I", listOf("Tangent, sine and cosine of acute angles",
                    "The trigonometric ratios of 30º, 45º and 60º",
                    "The use of calculators to read sine, cosine and tangent of angles between 0º and 360º",
                    "Inverse of trigonometric ratios",
                    "Angles of elevation and depression",
                    "Application of trigonometric ratios"
            )),
                MathTopics2("SEQUENCES AND SERIES", listOf("Patterns of sequence",
                        "Arithmetic Progression", "Sum of the first n terms of an AP",
                        "Geometric Progression (or Exponential sequence)",
                        "General term of a GP"
            )),
                MathTopics2("RIGID MOTION II AND ENLARGEMENT", listOf("Rotational symmetry",
                    "Rotation", "Enlargement", "Scale drawing",
                    "Areas and Volumes of similar figures"
            ))
            )
        }
    }
}


data class MathTopics3(val topic:String,val subtopic:List<String>){
    companion object{
        fun getData():List<MathTopics3>{
           return listOf(
               MathTopics3("SELECT A TOPIC", listOf("a","b","c")),
               MathTopics3("CONSTRUCTION", listOf("Construction of 75,105,135 and 150 degrees",
                       "Construction of Triangles and Quadrilaterals",
                       "Constructing loc"
           )),
               MathTopics3("MENSURATION II (SURFACE AREA, VOLUME OF SOLIDS AND THE EARTH AS A EARTH)",
                   listOf("Nets of prisms", "Surface Areas of Prisms",
               "Volume of prisms Surface",
               "Area of a Cone", "Volume of a Cone", "Surface Area of a Pyramid",
               "Volume of a Pyramid", "Surface area of a Sphere","Volume of a Sphere Distances of arcs of Spheres"
            )),
               MathTopics3("LOGICAL REASONING", listOf("Statements", "Negation of statements",
                       "Implications", "Validity of implications"
               )),
               MathTopics3("TRIGONOMETRY II", listOf("Graphs of trigonometric functions",
                   "Trigonometric equations"
           ))

           )
        }
    }
}
