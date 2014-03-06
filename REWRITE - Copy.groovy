            def record = []
			def od = []
			def field = []
			threshold = 0.8
			debug = false
			//20140122HV08
			def wqt = [[2,1,7], [8,3,5], [9,6,5], [10,12,9], [7,1,10], [12,3,1], [10,3,6], [2,3,8], [5,2,6], [11,7,1], [10,1,11], [1,6,12], [11,2,8], [11,8,4], [11,6,8], [12,2,10], [5,7,10], [11,8,9], [4,9,2], [10,7,6], [8,10,3], [7,2,6], [4,1,2], [9,1,6], [12,10,6], [6,3,9], [9,2,8], [7,4,6], [5,10,2], [5,7,3], [1,9,3], [4,7,5], [12,5,3], [11,3,7], [7,3,1], [8,10,2], [9,12,8], [1,9,2], [8,11,6], [3,11,9], [5,6,12], [9,5,4], [8,4,2], [4,6,2], [5,9,3], [12,2,7], [5,2,1], [4,1,7], [8,3,9], [7,4,10], [4,7,2], [9,10,7], [3,8,12], [9,8,1], [4,2,7], [9,5,6], [12,11,3], [10,12,1], [3,1,8], [8,7,5], [2,5,3], [9,12,3], [4,11,8], [9,3,8], [8,1,4], [7,6,5], [1,8,10], [2,11,7], [3,9,4], [9,12,6], [5,1,8], [11,8,4], [2,9,12], [11,7,9], [6,10,7], [4,3,10], [6,3,9], [7,4,6], [8,4,5], [9,10,1], [8,2,6], [2,5,6], [5,4,6], [8,6,11], [12,5,8], [2,6,10], [1,7,10], [9,7,4], [9,11,5], [10,3,7], [3,5,11], [7,4,2], [12,2,9], [4,2,9], [4,6,9], [1,4,2], [10,2,3], [3,9,7], [11,5,9], [7,8,6], [1,3,11], [4,3,7], [10,3,4], [5,7,1], [8,10,4], [12,9,5], [6,7,11], [1,4,8], [12,9,2], [7,8,9], [5,7,8], [3,7,6], [10,12,6], [7,12,8], [3,9,1], [12,11,4], [1,5,8], [1,5,7], [4,3,9], [6,2,10], [10,12,3], [5,1,7], [4,11,12], [7,1,9], [7,6,12], [7,6,11], [5,12,1], [11,7,2], [5,12,6], [4,8,1], [3,8,7], [1,7,9], [3,10,1], [10,6,8], [6,1,4], [11,9,6], [7,10,12], [7,9,2]]
			//println wqt.size()
			
			def pointer = 0
			def odwqt = []

  
  // Directory path here
  String path = "."; 
 
  String files;
  File folder = new File(path);
  File[] listOfFiles = folder.listFiles(); 
 
  for (int iz = 0; iz < listOfFiles.length; iz++) 
  {
 
   if (listOfFiles[iz].isFile()) 
   {
   files = listOfFiles[iz].getName();
       if (files.endsWith(".txt") || files.endsWith(".TXT"))
       {

        BufferedReader br = null;
 
        try {
 
            String sCurrentLine;
 
            br = new BufferedReader(new FileReader(files.substring(0,12)+".txt"));
            def ff = []
            def fm = []
            //def od = []
 
            while ((sCurrentLine = br.readLine()) != null) {
                z = sCurrentLine.split("oddRecord").findAll { it.contains("TimeID") }.size()
                //println z
				//println files.substring(0,12)
                sCurrentLine.split("oddRecord").each() {
                if(it.contains("TimeID")){          
                    def row = ""
					def p = 1
                    for(int i=1;i<=it.split("\"o\"").size()-1;i++){
						while(!it.contains(("\"t"+p+"-").toString())){
							row = row + "999  \t"
							p++
						}					
					
                        row = row + it.split("\"o\"")[i].substring(2, it.split("\"o\"")[i].indexOf("n")-3) + "  \t"
						p++
                    }
                    if(it.contains("\"winHistory2\"")){
                        row = row + "<<"
                    }
                    if(it.contains("\"winHistory22\"")){
                        row = row + "<<"
                    }      
                    //println row
                    
                    if(it.contains("\"winHistory2\"")){
                        //println '1----'
						def p1 = 1
                    for(int i=1;i<=it.split("\"o\"").size()-1;i++){
						while(!it.contains(("\"t"+p1+"-").toString())){
							ff.add("999")
							p1++
							debug = true
							
						}					
                        ff.add(it.split("\"o\"")[i].substring(2, it.split("\"o\"")[i].indexOf("n")-3))
						p1++
                    }                        
                    }
                    if(it.contains("\"winHistory22\"")){
                        //println 'last----'
						def pl = 1
                    for(int i=1;i<=it.split("\"o\"").size()-1;i++){
						while(!it.contains(("\"t"+pl+"-").toString())){
							fm.add("999")
							pl++
						}					
                        fm.add(it.split("\"o\"")[i].substring(2, it.split("\"o\"")[i].indexOf("n")-3))
						pl++
                    }                        
                    }                          
                }
            }
            }
            //println ff
            //println fm
            for(int i=0;i<ff.size();i++){
                od.add(ff[i].toDouble()/fm[i].toDouble())
				if(ff[i].toDouble()/fm[i].toDouble() < threshold){
					field.add(files.substring(0,12)+"_"+ (i+1).toString())
					odwqt.add(wqt[pointer])
				}
            }
            //println od
			if(debug == true){
				//System.in.read()
				debug = false
			}
 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		pointer = pointer + 1
        }
     }
  }
		//println od.findAll {it<threshold}
		//println field
		//println odwqt
		//println od.findAll {it<threshold}.size()
		for(int i=0;i<od.findAll {it<threshold}.size();i++){
			record.add([od.findAll {it<threshold}[i],field[i], odwqt[i]])
		}
		for(r in record){
			if(r[1].substring(13,r[1].length()).toInteger() == r[2][0]){
				r.add("W")
			} else if(r[1].substring(13,r[1].length()).toInteger() == r[2][1] || r[1].substring(13,r[1].length()).toInteger() == r[2][2]) {
				r.add("P")
			} else {
				r.add("N")
			}
		}
		//println record.sort {it[0]}
		//println record.sort {it[0]}.size()
		//println field.size()
		//println odwqt.size()

		
		def cdf = record.sort {it[0]}
		
		def wn = 0
		for(c in cdf){
			if(c[3] == "W"){
				wn = wn + 1
			}
			c.add(wn)
		}
		
		for(int i=0;i<cdf.size();i++){
			cdf[i].add(cdf[i][4].toDouble()/(i+1))
		}
		
		//println cdf
		println cdf.size()
		//cdf.collect { it[5] }.each() { print it + ','}
		//println()
for(int i = 0; i <= 100 ; i++)		{
	println i + ': ' + cdf[i]
}
		
		System.in.read()