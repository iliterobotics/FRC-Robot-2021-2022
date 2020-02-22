package us.ilite.robot.auto.paths;

import com.team319.trajectory.Path;

public class Loop extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,32.3276,-5.4300,0.0024,0.1200,6.0000,0.0000,0.0024,0.1200,6.0000,0.0000,0.0024,0.1200,6.0000,0.0000,-3.1416},
				{0.0200,32.3228,-5.4300,0.0072,0.2396,5.9822,-0.8902,0.0072,0.2400,6.0000,0.0000,0.0072,0.2404,6.0178,0.8902,-3.1416},
				{0.0200,32.3156,-5.4300,0.0144,0.3588,5.9579,-1.2159,0.0144,0.3600,6.0000,0.0000,0.0144,0.3612,6.0421,1.2159,-3.1416},
				{0.0200,32.3060,-5.4300,0.0239,0.4772,5.9184,-1.9731,0.0240,0.4800,6.0000,0.0000,0.0241,0.4828,6.0816,1.9731,-3.1415},
				{0.0200,32.2940,-5.4300,0.0358,0.5945,5.8665,-2.5975,0.0360,0.6000,6.0000,0.0000,0.0362,0.6055,6.1335,2.5975,-3.1414},
				{0.0200,32.2796,-5.4300,0.0500,0.7106,5.8026,-3.1941,0.0504,0.7200,6.0000,0.0000,0.0508,0.7294,6.1974,3.1941,-3.1412},
				{0.0200,32.2628,-5.4300,0.0665,0.8251,5.7275,-3.7567,0.0672,0.8400,6.0000,0.0000,0.0679,0.8549,6.2725,3.7567,-3.1410},
				{0.0200,32.2436,-5.4300,0.0853,0.9379,5.6419,-4.2793,0.0864,0.9600,6.0000,0.0000,0.0875,0.9821,6.3581,4.2793,-3.1406},
				{0.0200,32.2220,-5.4301,0.1063,1.0489,5.5467,-4.7567,0.1080,1.0800,6.0000,0.0000,0.1097,1.1111,6.4533,4.7567,-3.1400},
				{0.0200,32.1980,-5.4301,0.1294,1.1577,5.4431,-5.1840,0.1320,1.2000,6.0000,0.0000,0.1346,1.2423,6.5569,5.1840,-3.1392},
				{0.0200,32.1716,-5.4302,0.1547,1.2644,5.3319,-5.5568,0.1584,1.3200,6.0000,0.0000,0.1621,1.3756,6.6681,5.5567,-3.1382},
				{0.0200,32.1428,-5.4303,0.1821,1.3687,5.2145,-5.8709,0.1872,1.4400,6.0000,0.0000,0.1923,1.5113,6.7855,5.8708,-3.1369},
				{0.0200,32.1116,-5.4305,0.2115,1.4705,5.0920,-6.1226,0.2184,1.5600,6.0000,0.0000,0.2253,1.6495,6.9079,6.1225,-3.1352},
				{0.0200,32.0780,-5.4307,0.2429,1.5698,4.9659,-6.3083,0.2520,1.6800,6.0000,0.0000,0.2611,1.7902,7.0341,6.3081,-3.1332},
				{0.0200,32.0420,-5.4311,0.2762,1.6666,4.8374,-6.4243,0.2880,1.8000,6.0000,0.0000,0.2998,1.9334,7.1626,6.4240,-3.1307},
				{0.0200,32.0036,-5.4315,0.3114,1.7607,4.7081,-6.4666,0.3264,1.9200,6.0000,0.0000,0.3414,2.0793,7.2919,6.4662,-3.1278},
				{0.0200,31.9628,-5.4322,0.3485,1.8523,4.5794,-6.4310,0.3672,2.0400,6.0000,0.0000,0.3859,2.2277,7.4205,6.4305,-3.1243},
				{0.0200,31.9196,-5.4330,0.3873,1.9414,4.4532,-6.3126,0.4104,2.1600,6.0000,0.0000,0.4335,2.3786,7.5468,6.3120,-3.1203},
				{0.0200,31.8740,-5.4341,0.4279,2.0280,4.3311,-6.1057,0.4560,2.2800,6.0000,0.0000,0.4841,2.5320,7.6689,6.1050,-3.1157},
				{0.0200,31.8261,-5.4354,0.4701,2.1123,4.2150,-5.8039,0.5040,2.4000,6.0000,0.0000,0.5379,2.6877,7.7849,5.8030,-3.1103},
				{0.0200,31.7757,-5.4372,0.5140,2.1944,4.1070,-5.3997,0.5544,2.5200,6.0000,0.0000,0.5948,2.8455,7.8929,5.3987,-3.1043},
				{0.0200,31.7229,-5.4393,0.5595,2.2746,4.0093,-4.8848,0.6072,2.6400,6.0000,0.0000,0.6549,3.0054,7.9906,4.8836,-3.0976},
				{0.0200,31.6678,-5.4419,0.6065,2.3531,3.9243,-4.2502,0.6624,2.7600,6.0000,0.0000,0.7183,3.1669,8.0755,4.2488,-3.0901},
				{0.0200,31.6103,-5.4451,0.6551,2.4302,3.8546,-3.4867,0.7200,2.8800,6.0000,0.0000,0.7849,3.3298,8.1453,3.4852,-3.0818},
				{0.0200,31.5504,-5.4490,0.7053,2.5063,3.8029,-2.5854,0.7800,3.0000,6.0000,0.0000,0.8547,3.4937,8.1969,2.5838,-3.0727},
				{0.0200,31.4882,-5.4536,0.7569,2.5817,3.7721,-1.5385,0.8424,3.1200,6.0000,0.0000,0.9279,3.6583,8.2277,1.5368,-3.0628},
				{0.0200,31.4236,-5.4590,0.8100,2.6570,3.7653,-0.3402,0.9072,3.2400,6.0000,0.0000,1.0044,3.8230,8.2344,0.3385,-3.0521},
				{0.0200,31.3567,-5.4654,0.8647,2.7327,3.7855,1.0117,0.9744,3.3600,6.0000,0.0000,1.0841,3.9872,8.2142,-1.0134,-3.0405},
				{0.0200,31.2875,-5.4729,0.9209,2.8094,3.8358,2.5147,1.0440,3.4800,6.0000,0.0000,1.1671,4.1505,8.1638,-2.5163,-3.0281},
				{0.0200,31.2160,-5.4815,0.9786,2.8878,3.9190,4.1603,1.1160,3.6000,6.0000,0.0000,1.2533,4.3121,8.0806,-4.1616,-3.0150},
				{0.0200,31.1423,-5.4914,1.0380,2.9686,4.0377,5.9324,1.1904,3.7200,6.0000,0.0000,1.3428,4.4714,7.9619,-5.9334,-3.0011},
				{0.0200,31.0663,-5.5027,1.0991,3.0524,4.1938,7.8065,1.2672,3.8400,6.0000,0.0000,1.4353,4.6275,7.8058,-7.8070,-2.9866},
				{0.0200,30.9882,-5.5155,1.1619,3.1402,4.3888,9.7492,1.3464,3.9600,6.0000,0.0000,1.5309,4.7797,7.6108,-9.7491,-2.9715},
				{0.0200,30.9079,-5.5300,1.2265,3.2327,4.6232,11.7187,1.4280,4.0800,6.0000,0.0000,1.6295,4.9272,7.3765,-11.7179,-2.9559},
				{0.0200,30.8254,-5.5461,1.2931,3.3306,4.8965,13.6663,1.5120,4.2000,6.0000,0.0000,1.7308,5.0693,7.1032,-13.6647,-2.9399},
				{0.0200,30.7409,-5.5641,1.3618,3.4348,5.2072,15.5382,1.5984,4.3200,6.0000,0.0000,1.8350,5.2051,6.7925,-15.5357,-2.9236},
				{0.0200,30.6544,-5.5841,1.4327,3.5458,5.5528,17.2795,1.6872,4.4400,6.0000,0.0000,1.9416,5.3341,6.4469,-17.2763,-2.9071},
				{0.0200,30.5659,-5.6060,1.5060,3.6644,5.9296,18.8382,1.7784,4.5600,6.0000,0.0000,2.0507,5.4555,6.0702,-18.8342,-2.8906},
				{0.0200,30.4754,-5.6300,1.5819,3.7911,6.3330,20.1694,1.8720,4.6800,6.0000,0.0000,2.1621,5.5688,5.6670,-20.1647,-2.8742},
				{0.0200,30.3830,-5.6561,1.6604,3.9262,6.7578,21.2398,1.9680,4.8000,6.0000,0.0000,2.2756,5.6737,5.2423,-21.2346,-2.8581},
				{0.0200,30.2888,-5.6844,1.7418,4.0702,7.1984,22.0311,2.0664,4.9200,6.0000,0.0000,2.3910,5.7697,4.8017,-22.0256,-2.8424},
				{0.0200,30.1927,-5.7148,1.8263,4.2232,7.6492,22.5419,2.1672,5.0400,6.0000,0.0000,2.5081,5.8567,4.3510,-22.5364,-2.8274},
				{0.0200,30.0948,-5.7474,1.9140,4.3853,8.1050,22.7884,2.2704,5.1600,6.0000,0.0000,2.6268,5.9346,3.8954,-22.7831,-2.8131},
				{0.0200,29.9950,-5.7822,2.0051,4.5565,8.5611,22.8026,2.3760,5.2800,6.0000,0.0000,2.7469,6.0034,3.4394,-22.7978,-2.7997},
				{0.0200,29.8935,-5.8190,2.0998,4.7368,9.0137,22.6292,2.4840,5.4000,6.0000,0.0000,2.8681,6.0632,2.9869,-22.6253,-2.7875},
				{0.0200,29.7902,-5.8579,2.1983,4.9260,9.4601,22.3211,2.5944,5.5200,6.0000,0.0000,2.9904,6.1140,2.5405,-22.3183,-2.7766},
				{0.0200,29.6850,-5.8986,2.3008,5.1240,9.8987,21.9335,2.7072,5.6400,6.0000,0.0000,3.1135,6.1560,2.1019,-21.9322,-2.7671},
				{0.0200,29.5780,-5.9412,2.4074,5.3305,10.3291,21.5184,2.8224,5.7600,6.0000,0.0000,3.2373,6.1894,1.6715,-21.5189,-2.7591},
				{0.0200,29.4690,-5.9855,2.5183,5.5456,10.7515,21.1178,2.9400,5.8800,6.0000,0.0000,3.3616,6.2144,1.2491,-21.1203,-2.7530},
				{0.0200,29.3581,-6.0312,2.6337,5.7689,11.1666,20.7577,3.0600,6.0000,6.0000,0.0000,3.4862,6.2311,0.8339,-20.7625,-2.7487},
				{0.0200,29.2451,-6.0782,2.7537,6.0004,11.5755,20.4419,3.1824,6.1200,6.0000,0.0000,3.6110,6.2396,0.4249,-20.4493,-2.7465},
				{0.0200,29.1299,-6.1263,2.8785,6.2400,11.9784,20.1449,3.3072,6.2400,6.0000,0.0000,3.7358,6.2400,0.0218,-20.1552,-2.7465},
				{0.0200,29.0124,-6.1751,3.0083,6.4875,12.3745,19.8054,3.4344,6.3600,6.0000,0.0000,3.8605,6.2325,-0.3746,-19.8189,-2.7489},
				{0.0200,28.8926,-6.2244,3.1431,6.7427,12.7608,19.3180,3.5640,6.4800,6.0000,0.0000,3.9848,6.2173,-0.7613,-19.3350,-2.7537},
				{0.0200,28.7702,-6.2739,3.2832,7.0053,13.1313,18.5245,3.6960,6.6000,6.0000,0.0000,4.1087,6.1947,-1.1322,-18.5453,-2.7612},
				{0.0200,28.6452,-6.3232,3.4287,7.2748,13.4755,17.2068,3.8304,6.7200,6.0000,0.0000,4.2320,6.1651,-1.4768,-17.2314,-2.7714},
				{0.0200,28.5173,-6.3719,3.5797,7.5504,13.7771,15.0806,3.9672,6.8400,6.0000,0.0000,4.3546,6.1295,-1.7790,-15.1088,-2.7845},
				{0.0200,28.3865,-6.4195,3.7364,7.8306,14.0130,11.7975,4.1064,6.9600,6.0000,0.0000,4.4764,6.0892,-2.0156,-11.8287,-2.8006},
				{0.0200,28.2527,-6.4657,3.8986,8.1137,14.1522,6.9585,4.2480,7.0800,6.0000,0.0000,4.5973,6.0461,-2.1554,-6.9915,-2.8196},
				{0.0200,28.1156,-6.5097,4.0666,8.3968,14.1552,0.1497,4.3920,7.2000,6.0000,0.0000,4.7174,6.0029,-2.1591,-0.1822,-2.8417},
				{0.0200,27.9752,-6.5513,4.2401,8.6763,13.9753,-8.9945,4.5384,7.3200,6.0000,0.0000,4.8367,5.9634,-1.9798,8.9658,-2.8667},
				{0.0200,27.8315,-6.5897,4.4190,8.9475,13.5614,-20.6947,4.6872,7.4400,6.0000,0.0000,4.9553,5.9320,-1.5663,20.6741,-2.8945},
				{0.0200,27.6843,-6.6245,4.6031,9.2048,12.8633,-34.9045,4.8384,7.5600,6.0000,0.0000,5.0736,5.9147,-0.8683,34.8973,-2.9248},
				{0.0200,27.5338,-6.6551,4.7920,9.4416,11.8395,-51.1903,4.9920,7.6800,6.0000,0.0000,5.1919,5.9178,0.1557,51.2019,-2.9573},
				{0.0200,27.3847,-6.6804,4.9790,9.3534,-4.4090,-812.4263,5.1432,7.5600,-6.0000,0.0000,5.3073,5.7659,-7.5917,-387.3683,-2.9903},
				{0.0200,27.2373,-6.7004,5.1636,9.2301,-6.1643,-87.7649,5.2920,7.4400,-6.0000,0.0000,5.4202,5.6492,-5.8350,87.8327,-3.0233},
				{0.0200,27.0917,-6.7153,5.3451,9.0726,-7.8766,-85.6148,5.4384,7.3200,-6.0000,0.0000,5.5316,5.5668,-4.1215,85.6774,-3.0557},
				{0.0200,26.9480,-6.7254,5.5227,8.8830,-9.4788,-80.1107,5.5824,7.2000,-6.0000,0.0000,5.6419,5.5164,-2.5182,80.1626,-3.0867},
				{0.0200,26.8065,-6.7311,5.6960,8.6646,-10.9185,-71.9828,5.7240,7.0800,-6.0000,0.0000,5.7518,5.4949,-1.0778,72.0200,-3.1159},
				{0.0200,26.6674,-6.7328,5.8645,8.4213,-12.1638,-62.2680,5.8632,6.9600,-6.0000,0.0000,5.8618,5.4983,0.1680,62.2892,-3.1428},
				{0.0200,26.5306,-6.7309,6.0276,8.1572,-13.2056,-52.0886,6.0000,6.8400,-6.0000,0.0000,5.9722,5.5224,1.2099,52.0942,-3.1671},
				{0.0200,26.3963,-6.7260,6.1851,7.8761,-14.0547,-42.4524,6.1344,6.7200,-6.0000,0.0000,6.0835,5.5636,2.0588,42.4446,-3.1884},
				{0.0200,26.2645,-6.7186,6.3368,7.5814,-14.7372,-34.1251,6.2664,6.6000,-6.0000,0.0000,6.1959,5.6184,2.7409,34.1067,-3.2065},
				{0.0200,26.1352,-6.7092,6.4823,7.2756,-15.2889,-27.5851,6.3960,6.4800,-6.0000,0.0000,6.3096,5.6843,3.2921,27.5588,-3.2212},
				{0.0200,26.0085,-6.6983,6.6215,6.9606,-15.7497,-23.0429,6.5232,6.3600,-6.0000,0.0000,6.4247,5.7593,3.7523,23.0112,-3.2323},
				{0.0200,25.8842,-6.6866,6.7542,6.6374,-16.1597,-20.4982,6.6480,6.2400,-6.0000,0.0000,6.5416,5.8426,4.1615,20.4629,-3.2396},
				{0.0200,25.7625,-6.6743,6.8804,6.3063,-16.5557,-19.8020,6.7704,6.1200,-6.0000,0.0000,6.6603,5.9337,4.5568,19.7641,-3.2430},
				{0.0200,25.7200,-6.6700,6.9233,2.1475,-207.9392,-9569.1724,6.8131,6.0000,-6.0000,0.0000,6.7027,2.1199,-190.6909,-9762.3885,-3.2433},
				{0.0200,25.7200,-6.6700,6.9233,0.0000,-107.3757,5028.1727,6.8131,5.8800,-6.0000,0.0000,6.7027,0.0000,-105.9938,4234.8551,-3.2433},
				{0.0200,25.6006,-6.6578,7.0434,6.0025,300.1234,20374.9588,6.9331,6.0000,6.0000,0.0000,6.8226,5.9975,299.8766,20293.5196,-3.2433},
				{0.0200,25.4812,-6.6456,7.1634,6.0002,-0.1153,-15011.9350,7.0531,6.0000,6.0000,0.0000,6.9426,5.9998,0.1153,-14988.0650,-3.2433},
				{0.0200,25.3619,-6.6335,7.2836,6.0137,0.6750,39.5119,7.1731,6.0000,6.0000,0.0000,7.0623,5.9863,-0.6750,-39.5120,-3.2436},
				{0.0200,25.2425,-6.6212,7.4044,6.0374,1.1877,25.6372,7.2931,6.0000,6.0000,0.0000,7.1816,5.9626,-1.1877,-25.6377,-3.2443},
				{0.0200,25.1231,-6.6089,7.5258,6.0709,1.6742,24.3245,7.4131,6.0000,6.0000,0.0000,7.3002,5.9291,-1.6742,-24.3255,-3.2456},
				{0.0200,25.0038,-6.5963,7.6481,6.1137,2.1406,23.3210,7.5331,6.0000,6.0000,0.0000,7.4179,5.8863,-2.1407,-23.3228,-3.2477},
				{0.0200,24.8845,-6.5834,7.7714,6.1656,2.5946,22.6982,7.6531,6.0000,6.0000,0.0000,7.5346,5.8344,-2.5947,-22.7010,-3.2507},
				{0.0200,24.7652,-6.5701,7.8959,6.2265,3.0449,22.5126,7.7731,6.0000,6.0000,0.0000,7.6501,5.7735,-3.0451,-22.5165,-3.2549},
				{0.0200,24.6460,-6.5563,8.0218,6.2965,3.5011,22.8102,7.8931,6.0000,6.0000,0.0000,7.7641,5.7035,-3.5014,-22.8156,-3.2604},
				{0.0200,24.5269,-6.5417,8.1494,6.3760,3.9737,23.6313,8.0131,6.0000,6.0000,0.0000,7.8766,5.6240,-3.9741,-23.6384,-3.2673},
				{0.0200,24.4079,-6.5261,8.2787,6.4655,4.4739,25.0123,8.1331,6.0000,6.0000,0.0000,7.9873,5.5345,-4.4746,-25.0216,-3.2759},
				{0.0200,24.2891,-6.5095,8.4100,6.5658,5.0137,26.9868,8.2531,6.0000,6.0000,0.0000,8.0960,5.4342,-5.0145,-26.9988,-3.2863},
				{0.0200,24.1704,-6.4915,8.5435,6.6779,5.6053,29.5828,8.3731,6.0000,6.0000,0.0000,8.2024,5.3221,-5.6065,-29.5981,-3.2988},
				{0.0200,24.0521,-6.4718,8.6796,6.8031,6.2616,32.8156,8.4931,6.0000,6.0000,0.0000,8.3064,5.1968,-6.2632,-32.8351,-3.3136},
				{0.0200,23.9340,-6.4503,8.8185,6.9430,6.9951,36.6737,8.6131,6.0000,6.0000,0.0000,8.4075,5.0569,-6.9972,-36.6987,-3.3310},
				{0.0200,23.8164,-6.4266,8.9605,7.0993,7.8170,41.0942,8.7331,6.0000,6.0000,0.0000,8.5055,4.9005,-7.8197,-41.1261,-3.3513},
				{0.0200,23.6993,-6.4002,9.1059,7.2740,8.7354,45.9219,8.8531,6.0000,6.0000,0.0000,8.6000,4.7257,-8.7389,-45.9624,-3.3747},
				{0.0200,23.5829,-6.3710,9.2553,7.4691,9.7523,50.8461,8.9731,6.0000,6.0000,0.0000,8.6906,4.5305,-9.7569,-50.8972,-3.4018},
				{0.0200,23.4675,-6.3384,9.4090,7.6863,10.8585,55.3070,9.0931,6.0000,6.0000,0.0000,8.7769,4.3133,-10.8643,-55.3709,-3.4329},
				{0.0200,23.3531,-6.3019,9.5676,7.9268,12.0259,58.3712,9.2131,6.0000,6.0000,0.0000,8.8583,4.0726,-12.0333,-58.4493,-3.4684},
				{0.0200,23.2403,-6.2612,9.7314,8.1907,13.1976,58.5838,9.3331,6.0000,6.0000,0.0000,8.9345,3.8085,-13.2068,-58.6764,-3.5088},
				{0.0200,23.1293,-6.2156,9.9009,8.4762,14.2745,53.8468,9.4531,6.0000,6.0000,0.0000,9.0050,3.5227,-14.2858,-53.9513,-3.5545},
				{0.0200,23.0206,-6.1647,10.0765,8.7783,15.1031,41.4308,9.5731,6.0000,6.0000,0.0000,9.0694,3.2204,-15.1166,-41.5393,-3.6057},
				{0.0200,22.9149,-6.1080,10.2582,9.0877,15.4695,18.3178,9.6931,6.0000,6.0000,0.0000,9.1276,2.9107,-15.4849,-18.4149,-3.6627},
				{0.0200,22.8127,-6.0451,10.4460,9.3899,15.1121,-17.8681,9.8131,6.0000,6.0000,0.0000,9.1797,2.6081,-15.1288,17.8068,-3.7252},
				{0.0200,22.7148,-5.9757,10.6393,9.6653,13.7679,-67.2136,9.9331,6.0000,6.0000,0.0000,9.2264,2.3324,-13.7844,67.2193,-3.7928},
				{0.0200,22.6220,-5.8997,10.8371,9.8904,11.2573,-125.5300,10.0531,6.0000,6.0000,0.0000,9.2685,2.1070,-11.2718,125.6321,-3.8645},
				{0.0200,22.5351,-5.8170,11.0380,10.0423,7.5918,-183.2717,10.1731,6.0000,6.0000,0.0000,9.3076,1.9550,-7.6021,183.4828,-3.9391},
				{0.0200,22.4546,-5.7281,11.2401,10.1032,3.0499,-227.0961,10.2931,6.0000,6.0000,0.0000,9.3455,1.8939,-3.0542,227.3979,-4.0148},
				{0.0200,22.3810,-5.6333,11.4414,10.0664,-1.8440,-244.6955,10.4131,6.0000,6.0000,0.0000,9.3841,1.9308,1.8466,245.0364,-4.0898},
				{0.0200,22.3146,-5.5333,11.6401,9.9372,-6.4583,-230.7139,10.5331,6.0000,6.0000,0.0000,9.4253,2.0602,6.4671,231.0266,-4.1624},
				{0.0200,22.2555,-5.4290,11.8348,9.7322,-10.2509,-189.6310,10.6531,6.0000,6.0000,0.0000,9.4706,2.2654,10.2643,189.8604,-4.2312},
				{0.0200,22.2035,-5.3209,12.0242,9.4738,-12.9172,-133.3157,10.7731,6.0000,6.0000,0.0000,9.5211,2.5241,12.9331,133.4384,-4.2953},
				{0.0200,22.1581,-5.2098,12.2080,9.1854,-14.4216,-75.2181,10.8931,6.0000,6.0000,0.0000,9.5774,2.8129,14.4379,75.2426,-4.3540},
				{0.0200,22.1191,-5.0963,12.3857,8.8868,-14.9281,-25.3237,11.0131,6.0000,6.0000,0.0000,9.6396,3.1117,14.9435,25.2774,-4.4072},
				{0.0200,22.0858,-4.9810,12.5576,8.5930,-14.6941,11.6981,11.1331,6.0000,6.0000,0.0000,9.7077,3.4059,14.7078,-11.7846,-4.4551},
				{0.0200,22.0578,-4.8643,12.7238,8.3133,-13.9824,35.5860,11.2531,6.0000,6.0000,0.0000,9.7815,3.6858,13.9940,-35.6876,-4.4977},
				{0.0200,22.0345,-4.7466,12.8849,8.0531,-13.0118,48.5306,11.3731,6.0000,6.0000,0.0000,9.8604,3.9462,13.0214,-48.6309,-4.5356},
				{0.0200,22.0154,-4.6282,13.0412,7.8142,-11.9421,53.4826,11.4931,6.0000,6.0000,0.0000,9.9441,4.1852,11.9500,-53.5730,-4.5690},
				{0.0200,22.0000,-4.5092,13.1931,7.5967,-10.8788,53.1650,11.6131,6.0000,6.0000,0.0000,10.0321,4.4029,10.8851,-53.2422,-4.5985},
				{0.0200,21.9880,-4.3898,13.3411,7.3990,-9.8850,49.6883,11.7331,6.0000,6.0000,0.0000,10.1242,4.6007,9.8901,-49.7520,-4.6242},
				{0.0200,21.9788,-4.2701,13.4855,7.2191,-8.9947,44.5153,11.8531,6.0000,6.0000,0.0000,10.2198,4.7807,8.9987,-44.5669,-4.6467},
				{0.0200,21.9721,-4.1503,13.6266,7.0546,-8.2233,38.5736,11.9731,6.0000,6.0000,0.0000,10.3187,4.9452,8.2264,-38.6149,-4.6662},
				{0.0200,21.9675,-4.0304,13.7646,6.9031,-7.5753,32.3977,12.0931,6.0000,6.0000,0.0000,10.4206,5.0968,7.5778,-32.4305,-4.6828},
				{0.0200,21.9648,-3.9104,13.8999,6.7621,-7.0503,26.2520,12.2131,6.0000,6.0000,0.0000,10.5254,5.2378,7.0523,-26.2783,-4.6969},
				{0.0200,21.9637,-3.7904,14.0324,6.6292,-6.6458,20.2222,12.3331,6.0000,6.0000,0.0000,10.6328,5.3708,6.6474,-20.2434,-4.7085},
				{0.0200,21.9638,-3.6704,14.1625,6.5020,-6.3604,14.2734,12.4531,6.0000,6.0000,0.0000,10.7427,5.4980,6.3616,-14.2908,-4.7177},
				{0.0200,21.9649,-3.5505,14.2900,6.3781,-6.1947,8.2838,12.5731,6.0000,6.0000,0.0000,10.8552,5.6219,6.1956,-8.2987,-4.7247},
				{0.0200,21.9666,-3.4305,14.4151,6.2550,-6.1535,2.0579,12.6931,6.0000,6.0000,0.0000,10.9701,5.7450,6.1542,-2.0711,-4.7294},
				{0.0200,21.9688,-3.3105,14.5377,6.1301,-6.2471,-4.6785,12.8131,6.0000,6.0000,0.0000,11.0875,5.8699,6.2475,4.6659,-4.7318},
				{0.0200,21.9712,-3.1905,14.6579,6.0085,-6.0800,8.3531,12.9331,6.0000,6.0000,0.0000,11.2073,5.9915,6.0802,-8.3663,-4.7319},
				{0.0200,21.9734,-3.0705,14.7767,5.9418,-3.3351,137.2449,13.0531,6.0000,6.0000,0.0000,11.3285,6.0582,3.3351,-137.2535,-4.7309},
				{0.0200,21.9755,-2.9506,14.8949,5.9059,-1.7927,77.1238,13.1731,6.0000,6.0000,0.0000,11.4503,6.0941,1.7926,-77.1247,-4.7291},
				{0.0200,21.9774,-2.8306,15.0127,5.8923,-0.6824,55.5108,13.2931,6.0000,6.0000,0.0000,11.5725,6.1077,0.6824,-55.5097,-4.7271},
				{0.0200,21.9791,-2.7106,15.1307,5.8991,0.3423,51.2361,13.4131,6.0000,6.0000,0.0000,11.6945,6.1009,-0.3423,-51.2343,-4.7253},
				{0.0200,21.9805,-2.5906,15.2492,5.9247,1.2816,46.9658,13.5331,6.0000,6.0000,0.0000,11.8160,6.0753,-1.2816,-46.9644,-4.7239},
				{0.0200,21.9818,-2.4706,15.3685,5.9676,2.1431,43.0746,13.6531,6.0000,6.0000,0.0000,11.9367,6.0324,-2.1430,-43.0746,-4.7233},
				{0.0200,21.9832,-2.3506,15.4891,6.0264,2.9398,39.8361,13.7731,6.0000,6.0000,0.0000,12.0561,5.9736,-2.9398,-39.8379,-4.7238},
				{0.0200,21.9846,-2.2306,15.6111,6.1002,3.6886,37.4400,13.8931,6.0000,6.0000,0.0000,12.1741,5.8998,-3.6887,-37.4442,-4.7256},
				{0.0200,21.9864,-2.1106,15.7348,6.1883,4.4088,36.0118,14.0131,6.0000,6.0000,0.0000,12.2904,5.8116,-4.4091,-36.0187,-4.7291},
				{0.0200,21.9887,-1.9906,15.8607,6.2908,5.1215,35.6304,14.1331,6.0000,6.0000,0.0000,12.4046,5.7092,-5.1219,-35.6404,-4.7345},
				{0.0200,21.9917,-1.8707,15.9888,6.4077,5.8483,36.3414,14.2531,6.0000,6.0000,0.0000,12.5164,5.5922,-5.8490,-36.3552,-4.7420},
				{0.0200,21.9958,-1.7508,16.1196,6.5400,6.6116,38.1642,14.3731,6.0000,6.0000,0.0000,12.6256,5.4600,-6.6126,-38.1827,-4.7519},
				{0.0200,22.0013,-1.6309,16.2534,6.6886,7.4334,41.0893,14.4931,6.0000,6.0000,0.0000,12.7318,5.3113,-7.4349,-41.1137,-4.7646},
				{0.0200,22.0085,-1.5111,16.3905,6.8553,8.3346,45.0627,14.6131,6.0000,6.0000,0.0000,12.8347,5.1445,-8.3368,-45.0944,-4.7804},
				{0.0200,22.0177,-1.3915,16.5313,7.0420,9.3336,49.9498,14.7331,6.0000,6.0000,0.0000,12.9339,4.9578,-9.3366,-49.9909,-4.7996},
				{0.0200,22.0295,-1.2720,16.6763,7.2509,10.4431,55.4724,14.8531,6.0000,6.0000,0.0000,13.0289,4.7489,-10.4471,-55.5254,-4.8227},
				{0.0200,22.0443,-1.1530,16.8260,7.4842,11.6652,61.1052,14.9731,6.0000,6.0000,0.0000,13.1192,4.5155,-11.6706,-61.1728,-4.8500},
				{0.0200,22.0626,-1.0344,16.9809,7.7438,12.9836,65.9222,15.0931,6.0000,6.0000,0.0000,13.2043,4.2556,-12.9907,-66.0072,-4.8822},
				{0.0200,22.0850,-0.9165,17.1415,8.0309,14.3515,68.3929,15.2131,6.0000,6.0000,0.0000,13.2836,3.9684,-14.3607,-68.4971,-4.9196},
				{0.0200,22.1122,-0.7996,17.3084,8.3444,15.6747,66.1608,15.3331,6.0000,6.0000,0.0000,13.3567,3.6547,-15.6863,-66.2833,-4.9628},
				{0.0200,22.1447,-0.6841,17.4820,8.6802,16.7930,55.9150,15.4531,6.0000,6.0000,0.0000,13.4231,3.3186,-16.8073,-56.0494,-5.0123},
				{0.0200,22.1833,-0.5705,17.6626,9.0295,17.4650,33.5995,15.5731,6.0000,6.0000,0.0000,13.4825,2.9689,-17.4819,-33.7303,-5.0681},
				{0.0200,22.2285,-0.4594,17.8501,9.3770,17.3720,-4.6480,15.6931,6.0000,6.0000,0.0000,13.5349,2.6211,-17.3909,4.5490,-5.1304},
				{0.0200,22.2808,-0.3514,18.0441,9.7002,16.1628,-60.4577,15.8131,6.0000,6.0000,0.0000,13.5809,2.2975,-16.1823,60.4304,-5.1986},
				{0.0200,22.3407,-0.2474,18.2436,9.9714,13.5571,-130.2869,15.9331,6.0000,6.0000,0.0000,13.6214,2.0260,-13.5748,130.3742,-5.2719},
				{0.0200,22.4082,-0.1483,18.4468,10.1613,9.4952,-203.0951,16.0531,6.0000,6.0000,0.0000,13.6581,1.8358,-9.5084,203.3225,-5.3486},
				{0.0200,22.4832,-0.0546,18.6517,10.2466,4.2667,-261.4233,16.1731,6.0000,6.0000,0.0000,13.6931,1.7503,-4.2729,261.7759,-5.4270},
				{0.0200,22.5653,0.0329,18.8561,10.2169,-1.4870,-287.6860,16.2931,6.0000,6.0000,0.0000,13.7287,1.7801,1.4891,288.1003,-5.5047},
				{0.0200,22.6538,0.1138,19.0576,10.0779,-6.9498,-273.1389,16.4131,6.0000,6.0000,0.0000,13.7671,1.9193,6.9596,273.5240,-5.5800},
				{0.0200,22.7480,0.1881,19.2546,9.8497,-11.4096,-222.9913,16.5331,6.0000,6.0000,0.0000,13.8100,2.1478,11.4250,223.2708,-5.6510},
				{0.0200,22.8471,0.2557,19.4458,9.5602,-14.4751,-153.2776,16.6531,6.0000,6.0000,0.0000,13.8588,2.4377,14.4934,153.4200,-5.7166},
				{0.0200,22.9503,0.3170,19.6306,9.2378,-16.1199,-82.2363,16.7731,6.0000,6.0000,0.0000,13.9140,2.7604,16.1385,82.2552,-5.7763},
				{0.0200,23.0567,0.3724,19.8087,8.9062,-16.5776,-22.8850,16.8931,6.0000,6.0000,0.0000,13.9759,3.0923,16.5949,22.8185,-5.8299},
				{0.0200,23.1658,0.4223,19.9803,8.5824,-16.1904,19.3604,17.0131,6.0000,6.0000,0.0000,14.0442,3.4165,16.2055,-19.4714,-5.8775},
				{0.0200,23.2771,0.4673,20.1459,8.2766,-15.2908,44.9768,17.1331,6.0000,6.0000,0.0000,14.1186,3.7225,15.3035,-45.1009,-5.9195},
				{0.0200,23.3900,0.5079,20.3058,7.9937,-14.1435,57.3681,17.2531,6.0000,6.0000,0.0000,14.1987,4.0056,14.1537,-57.4865,-5.9563},
				{0.0200,23.5042,0.5446,20.4605,7.7351,-12.9324,60.5506,17.3731,6.0000,6.0000,0.0000,14.2840,4.2644,12.9406,-60.6542,-5.9883},
				{0.0200,23.6195,0.5778,20.6104,7.4996,-11.7733,57.9578,17.4931,6.0000,6.0000,0.0000,14.3740,4.5000,11.7798,-58.0440,-6.0159},
				{0.0200,23.7356,0.6081,20.7561,7.2850,-10.7317,52.0791,17.6131,6.0000,6.0000,0.0000,14.4683,4.7147,10.7368,-52.1487,-6.0396},
				{0.0200,23.8524,0.6359,20.8979,7.0882,-9.8413,44.5198,17.7331,6.0000,6.0000,0.0000,14.5666,4.9116,9.8453,-44.5753,-6.0597},
				{0.0200,23.9696,0.6615,21.0360,6.9058,-9.1173,36.2022,17.8531,6.0000,6.0000,0.0000,14.6684,5.0941,9.1204,-36.2461,-6.0764},
				{0.0200,24.0872,0.6853,21.1707,6.7345,-8.5660,27.5640,17.9731,6.0000,6.0000,0.0000,14.7738,5.2654,8.5684,-27.5991,-6.0899},
				{0.0200,24.2051,0.7077,21.3021,6.5707,-8.1918,18.7103,18.0931,6.0000,6.0000,0.0000,14.8823,5.4293,8.1936,-18.7388,-6.1004},
				{0.0200,24.3232,0.7291,21.4303,6.4106,-8.0017,9.5051,18.2131,6.0000,6.0000,0.0000,14.9941,5.5894,8.0030,-9.5292,-6.1080},
				{0.0200,24.4414,0.7497,21.5554,6.2504,-8.0094,-0.3875,18.3331,6.0000,6.0000,0.0000,15.1091,5.7496,8.0103,0.3658,-6.1126},
				{0.0200,24.5597,0.7699,21.6771,6.0856,-8.2397,-11.5122,18.4531,6.0000,6.0000,0.0000,15.2274,5.9144,8.2401,11.4907,-6.1142},
				{0.0200,24.6780,0.7901,21.7981,6.0506,-1.7516,324.4030,18.5731,6.0000,6.0000,0.0000,15.3464,5.9494,1.7517,-324.4243,-6.1151},
				{0.0200,24.7963,0.8100,21.9210,6.1484,4.8918,332.1716,18.6931,6.0000,6.0000,0.0000,15.4634,5.8516,-4.8920,-332.1820,-6.1178},
				{0.0200,24.9147,0.8296,22.0459,6.2411,4.6327,-12.9535,18.8131,6.0000,6.0000,0.0000,15.5786,5.7589,-4.6331,12.9464,-6.1223},
				{0.0200,25.0332,0.8485,22.1725,6.3293,4.4103,-11.1227,18.9331,6.0000,6.0000,0.0000,15.6920,5.6707,-4.4107,11.1167,-6.1284},
				{0.0200,25.1519,0.8665,22.3007,6.4136,4.2166,-9.6859,19.0531,6.0000,6.0000,0.0000,15.8037,5.5863,-4.2171,9.6807,-6.1360},
				{0.0200,25.2706,0.8836,22.4306,6.4945,4.0444,-8.6083,19.1731,6.0000,6.0000,0.0000,15.9139,5.5054,-4.0450,8.6037,-6.1451},
				{0.0200,25.3896,0.8995,22.5621,6.5723,3.8872,-7.8606,19.2931,6.0000,6.0000,0.0000,16.0224,5.4277,-3.8879,7.8565,-6.1556},
				{0.0200,25.5087,0.9141,22.6950,6.6470,3.7388,-7.4196,19.4131,6.0000,6.0000,0.0000,16.1295,5.3529,-3.7396,7.4161,-6.1676},
				{0.0200,25.6280,0.9272,22.8294,6.7189,3.5935,-7.2663,19.5331,6.0000,6.0000,0.0000,16.2351,5.2810,-3.5943,7.2633,-6.1808},
				{0.0200,25.7474,0.9386,22.9651,6.7878,3.4458,-7.3832,19.6531,6.0000,6.0000,0.0000,16.3393,5.2121,-3.4467,7.3808,-6.1954},
				{0.0200,25.8670,0.9482,23.1022,6.8536,3.2908,-7.7525,19.7731,6.0000,6.0000,0.0000,16.4422,5.1462,-3.2917,7.7508,-6.2111},
				{0.0200,25.9868,0.9559,23.2405,6.9161,3.1237,-8.3539,19.8931,6.0000,6.0000,0.0000,16.5439,5.0837,-3.1246,8.3528,-6.2280},
				{0.0200,26.1067,0.9614,23.3800,6.9749,2.9404,-9.1622,20.0131,6.0000,6.0000,0.0000,16.6444,5.0249,-2.9414,9.1619,-6.2459},
				{0.0200,26.2266,0.9648,23.5206,7.0297,2.7375,-10.1459,20.1331,6.0000,6.0000,0.0000,16.7438,4.9701,-2.7385,10.1465,-6.2649},
				{0.0200,26.3466,0.9658,23.6622,7.0799,2.5122,-11.2659,20.2531,6.0000,6.0000,0.0000,16.8422,4.9199,-2.5131,11.2674,-6.2848},
				{0.0200,26.4666,0.9644,23.8047,7.1252,2.2627,-12.4747,20.3731,6.0000,6.0000,0.0000,16.9397,4.8746,-2.2636,12.4773,-6.3056},
				{0.0200,26.5865,0.9604,23.9480,7.1649,1.9884,-13.7172,20.4931,6.0000,6.0000,0.0000,17.0364,4.8348,-1.9891,13.7209,-6.3271},
				{0.0200,26.7064,0.9538,24.0920,7.1987,1.6897,-14.9321,20.6131,6.0000,6.0000,0.0000,17.1324,4.8010,-1.6904,14.9369,-6.3492},
				{0.0200,26.8260,0.9446,24.2365,7.2261,1.3686,-16.0546,20.7331,6.0000,6.0000,0.0000,17.2279,4.7736,-1.3692,16.0604,-6.3718},
				{0.0200,26.9454,0.9326,24.3815,7.2467,1.0282,-17.0203,20.8531,6.0000,6.0000,0.0000,17.3230,4.7531,-1.0287,17.0269,-6.3947},
				{0.0200,27.0645,0.9179,24.5267,7.2601,0.6728,-17.7695,20.9731,6.0000,6.0000,0.0000,17.4178,4.7396,-0.6731,17.7767,-6.4180},
				{0.0200,27.1832,0.9004,24.6720,7.2663,0.3078,-18.2519,21.0931,6.0000,6.0000,0.0000,17.5124,4.7334,-0.3079,18.2596,-6.4413},
				{0.0200,27.3015,0.8801,24.8173,7.2651,-0.0608,-18.4310,21.2131,6.0000,6.0000,0.0000,17.6071,4.7347,0.0608,18.4390,-6.4646},
				{0.0200,27.4192,0.8571,24.9624,7.2565,-0.4266,-18.2874,21.3331,6.0000,6.0000,0.0000,17.7020,4.7432,0.4268,18.2952,-6.4878},
				{0.0200,27.5364,0.8314,25.1072,7.2409,-0.7830,-17.8200,21.4531,6.0000,6.0000,0.0000,17.7972,4.7589,0.7833,17.8275,-6.5107},
				{0.0200,27.6530,0.8030,25.2516,7.2184,-1.1239,-17.0463,21.5731,6.0000,6.0000,0.0000,17.8928,4.7813,1.1244,17.0532,-6.5331},
				{0.0200,27.7690,0.7721,25.3954,7.1895,-1.4439,-16.0004,21.6931,6.0000,6.0000,0.0000,17.9890,4.8102,1.4445,16.0065,-6.5551},
				{0.0200,27.8842,0.7386,25.5385,7.1548,-1.7385,-14.7292,21.8131,6.0000,6.0000,0.0000,18.0859,4.8450,1.7392,14.7343,-6.5764},
				{0.0200,27.9987,0.7028,25.6808,7.1147,-2.0043,-13.2884,21.9331,6.0000,6.0000,0.0000,18.1836,4.8851,2.0050,13.2924,-6.5969},
				{0.0200,28.1125,0.6646,25.8222,7.0699,-2.2390,-11.7373,22.0531,6.0000,6.0000,0.0000,18.2822,4.9299,2.2398,11.7402,-6.6166},
				{0.0200,28.2255,0.6243,25.9626,7.0211,-2.4417,-10.1342,22.1731,6.0000,6.0000,0.0000,18.3818,4.9788,2.4426,10.1360,-6.6355},
				{0.0200,28.3378,0.5819,26.1020,6.9688,-2.6123,-8.5328,22.2931,6.0000,6.0000,0.0000,18.4824,5.0310,2.6132,8.5336,-6.6533},
				{0.0200,28.4493,0.5375,26.2402,6.9138,-2.7519,-6.9793,22.4131,6.0000,6.0000,0.0000,18.5841,5.0861,2.7528,6.9791,-6.6702},
				{0.0200,28.5600,0.4914,26.3774,6.8565,-2.8621,-5.5102,22.5331,6.0000,6.0000,0.0000,18.6870,5.1434,2.8630,5.5092,-6.6860},
				{0.0200,28.6701,0.4435,26.5133,6.7976,-2.9452,-4.1522,22.6531,6.0000,6.0000,0.0000,18.7910,5.2023,2.9460,4.1506,-6.7007},
				{0.0200,28.7795,0.3941,26.6481,6.7375,-3.0036,-2.9225,22.7731,6.0000,6.0000,0.0000,18.8963,5.2624,3.0044,2.9203,-6.7143},
				{0.0200,28.8882,0.3433,26.7816,6.6767,-3.0402,-1.8291,22.8931,6.0000,6.0000,0.0000,19.0027,5.3232,3.0409,1.8264,-6.7267},
				{0.0200,28.9963,0.2912,26.9139,6.6156,-3.0577,-0.8726,23.0131,6.0000,6.0000,0.0000,19.1104,5.3843,3.0583,0.8697,-6.7381},
				{0.0200,29.1038,0.2379,27.0450,6.5544,-3.0586,-0.0478,23.1331,6.0000,6.0000,0.0000,19.2193,5.4455,3.0592,0.0446,-6.7483},
				{0.0200,29.2108,0.1836,27.1749,6.4935,-3.0455,0.6555,23.2531,6.0000,6.0000,0.0000,19.3295,5.5065,3.0460,-0.6588,-6.7574},
				{0.0200,29.3173,0.1284,27.3035,6.4331,-3.0205,1.2503,23.3731,6.0000,6.0000,0.0000,19.4408,5.5669,3.0210,-1.2536,-6.7654},
				{0.0200,29.4234,0.0724,27.4310,6.3734,-2.9855,1.7518,23.4931,6.0000,6.0000,0.0000,19.5533,5.6266,2.9859,-1.7551,-6.7723},
				{0.0200,29.5292,0.0157,27.5573,6.3146,-2.9419,2.1762,23.6131,6.0000,6.0000,0.0000,19.6670,5.6854,2.9423,-2.1794,-6.7781},
				{0.0200,29.6347,-0.0416,27.6824,6.2567,-2.8911,2.5401,23.7331,6.0000,6.0000,0.0000,19.7819,5.7433,2.8914,-2.5432,-6.7828},
				{0.0200,29.7399,-0.0992,27.8064,6.2000,-2.8339,2.8598,23.8531,6.0000,6.0000,0.0000,19.8979,5.7999,2.8342,-2.8628,-6.7865},
				{0.0200,29.8450,-0.1573,27.9293,6.1446,-2.7709,3.1510,23.9731,6.0000,6.0000,0.0000,20.0150,5.8554,2.7711,-3.1538,-6.7891},
				{0.0200,29.9499,-0.2155,28.0511,6.0906,-2.7024,3.4285,24.0931,6.0000,6.0000,0.0000,20.1332,5.9094,2.7025,-3.4313,-6.7908},
				{0.0200,30.0547,-0.2739,28.1719,6.0380,-2.6282,3.7063,24.2131,6.0000,6.0000,0.0000,20.2524,5.9620,2.6283,-3.7088,-6.7915},
				{0.0200,30.1596,-0.3323,28.2916,5.9871,-2.5483,3.9969,24.3331,6.0000,6.0000,0.0000,20.3727,6.0129,2.5483,-3.9992,-6.7913},
				{0.0200,30.2644,-0.3906,28.4104,5.9378,-2.4621,4.3117,24.4531,6.0000,6.0000,0.0000,20.4939,6.0622,2.4620,-4.3138,-6.7901},
				{0.0200,30.3673,-0.4476,28.5259,5.7731,-8.2367,-288.7320,24.5707,5.8800,-6.0000,0.0000,20.6137,5.9869,-3.7634,-311.2695,-6.7882},
				{0.0200,30.4682,-0.5032,28.6381,5.6130,-8.0053,11.5699,24.6859,5.7600,-6.0000,0.0000,20.7318,5.9070,-3.9948,-11.5709,-6.7855},
				{0.0200,30.5672,-0.5573,28.7473,5.4574,-7.7807,11.2301,24.7987,5.6400,-6.0000,0.0000,20.8483,5.8226,-4.2194,-11.2305,-6.7821},
				{0.0200,30.6642,-0.6099,28.8534,5.3061,-7.5630,10.8844,24.9091,5.5200,-6.0000,0.0000,20.9630,5.7339,-4.4371,-10.8845,-6.7781},
				{0.0200,30.7594,-0.6610,28.9566,5.1590,-7.3524,10.5291,25.0171,5.4000,-6.0000,0.0000,21.0758,5.6409,-4.6477,-10.5289,-6.7737},
				{0.0200,30.8526,-0.7105,29.0569,5.0161,-7.1492,10.1600,25.1227,5.2800,-6.0000,0.0000,21.1866,5.5439,-4.8509,-10.1595,-6.7688},
				{0.0200,30.9440,-0.7585,29.1544,4.8770,-6.9538,9.7727,25.2259,5.1600,-6.0000,0.0000,21.2955,5.4430,-5.0463,-9.7720,-6.7636},
				{0.0200,31.0335,-0.8048,29.2493,4.7417,-6.7665,9.3635,25.3267,5.0400,-6.0000,0.0000,21.4023,5.3383,-5.2335,-9.3628,-6.7581},
				{0.0200,31.1212,-0.8495,29.3415,4.6099,-6.5879,8.9297,25.4251,4.9200,-6.0000,0.0000,21.5069,5.2301,-5.4121,-8.9289,-6.7524},
				{0.0200,31.2069,-0.8927,29.4311,4.4815,-6.4185,8.4695,25.5211,4.8000,-6.0000,0.0000,21.6092,5.1185,-5.5815,-8.4687,-6.7465},
				{0.0200,31.2908,-0.9343,29.5182,4.3564,-6.2589,7.9824,25.6147,4.6800,-6.0000,0.0000,21.7093,5.0036,-5.7411,-7.9817,-6.7406},
				{0.0200,31.3727,-0.9743,29.6029,4.2342,-6.1095,7.4692,25.7059,4.5600,-6.0000,0.0000,21.8070,4.8858,-5.8905,-7.4685,-6.7346},
				{0.0200,31.4528,-1.0128,29.6852,4.1147,-5.9708,6.9318,25.7947,4.4400,-6.0000,0.0000,21.9023,4.7652,-6.0291,-6.9312,-6.7286},
				{0.0200,31.5309,-1.0498,29.7652,3.9979,-5.8434,6.3733,25.8811,4.3200,-6.0000,0.0000,21.9952,4.6421,-6.1566,-6.3728,-6.7226},
				{0.0200,31.6070,-1.0853,29.8428,3.8833,-5.7274,5.7977,25.9651,4.2000,-6.0000,0.0000,22.0855,4.5167,-6.2725,-5.7973,-6.7168},
				{0.0200,31.6811,-1.1193,29.9182,3.7709,-5.6232,5.2097,26.0467,4.0800,-6.0000,0.0000,22.1733,4.3891,-6.3767,-5.2094,-6.7111},
				{0.0200,31.7533,-1.1520,29.9915,3.6602,-5.5309,4.6145,26.1259,3.9600,-6.0000,0.0000,22.2585,4.2597,-6.4690,-4.6143,-6.7056},
				{0.0200,31.8234,-1.1833,30.0625,3.5512,-5.4506,4.0176,26.2027,3.8400,-6.0000,0.0000,22.3411,4.1288,-6.5494,-4.0175,-6.7002},
				{0.0200,31.8915,-1.2132,30.1313,3.4436,-5.3821,3.4247,26.2771,3.7200,-6.0000,0.0000,22.4210,3.9964,-6.6179,-3.4246,-6.6951},
				{0.0200,31.9576,-1.2419,30.1981,3.3371,-5.3253,2.8411,26.3491,3.6000,-6.0000,0.0000,22.4983,3.8629,-6.6747,-2.8412,-6.6903},
				{0.0200,32.0215,-1.2693,30.2627,3.2315,-5.2798,2.2722,26.4187,3.4800,-6.0000,0.0000,22.5728,3.7285,-6.7201,-2.2723,-6.6857},
				{0.0200,32.0834,-1.2955,30.3253,3.1266,-5.2454,1.7229,26.4859,3.3600,-6.0000,0.0000,22.6447,3.5934,-6.7546,-1.7231,-6.6814},
				{0.0200,32.1432,-1.3205,30.3857,3.0222,-5.2214,1.1976,26.5507,3.2400,-6.0000,0.0000,22.7138,3.4578,-6.7785,-1.1978,-6.6774},
				{0.0200,32.2009,-1.3444,30.4441,2.9180,-5.2074,0.7002,26.6131,3.1200,-6.0000,0.0000,22.7803,3.3220,-6.7925,-0.7004,-6.6737},
				{0.0200,32.2564,-1.3671,30.5003,2.8140,-5.2027,0.2339,26.6731,3.0000,-6.0000,0.0000,22.8440,3.1860,-6.7972,-0.2341,-6.6703},
				{0.0200,32.3098,-1.3887,30.5545,2.7098,-5.2067,-0.1983,26.7307,2.8800,-6.0000,0.0000,22.9050,3.0502,-6.7933,0.1981,-6.6671},
				{0.0200,32.3610,-1.4093,30.6066,2.6054,-5.2186,-0.5944,26.7859,2.7600,-6.0000,0.0000,22.9633,2.9146,-6.7814,0.5942,-6.6643},
				{0.0200,32.4100,-1.4289,30.6567,2.5007,-5.2376,-0.9526,26.8387,2.6400,-6.0000,0.0000,23.0189,2.7793,-6.7623,0.9524,-6.6617},
				{0.0200,32.4569,-1.4475,30.7046,2.3954,-5.2631,-1.2717,26.8891,2.5200,-6.0000,0.0000,23.0718,2.6446,-6.7369,1.2715,-6.6594},
				{0.0200,32.5016,-1.4651,30.7504,2.2896,-5.2941,-1.5510,26.9371,2.4000,-6.0000,0.0000,23.1220,2.5104,-6.7059,1.5508,-6.6574},
				{0.0200,32.5440,-1.4817,30.7940,2.1830,-5.3299,-1.7902,26.9827,2.2800,-6.0000,0.0000,23.1695,2.3770,-6.6701,1.7900,-6.6556},
				{0.0200,32.5843,-1.4974,30.8355,2.0756,-5.3697,-1.9894,27.0259,2.1600,-6.0000,0.0000,23.2144,2.2444,-6.6303,1.9892,-6.6540},
				{0.0200,32.6223,-1.5121,30.8749,1.9673,-5.4127,-2.1489,27.0667,2.0400,-6.0000,0.0000,23.2567,2.1127,-6.5873,2.1488,-6.6527},
				{0.0200,32.6581,-1.5260,30.9120,1.8581,-5.4581,-2.2695,27.1051,1.9200,-6.0000,0.0000,23.2963,1.9819,-6.5419,2.2694,-6.6515},
				{0.0200,32.6917,-1.5389,30.9470,1.7480,-5.5051,-2.3521,27.1411,1.8000,-6.0000,0.0000,23.3333,1.8520,-6.4949,2.3520,-6.6506},
				{0.0200,32.7231,-1.5510,30.9797,1.6370,-5.5531,-2.3980,27.1747,1.6800,-6.0000,0.0000,23.3678,1.7230,-6.4469,2.3979,-6.6498},
				{0.0200,32.7522,-1.5621,31.0102,1.5250,-5.6012,-2.4086,27.2059,1.5600,-6.0000,0.0000,23.3997,1.5950,-6.3988,2.4085,-6.6491},
				{0.0200,32.7791,-1.5724,31.0385,1.4120,-5.6489,-2.3855,27.2347,1.4400,-6.0000,0.0000,23.4291,1.4680,-6.3511,2.3854,-6.6486},
				{0.0200,32.8038,-1.5819,31.0644,1.2981,-5.6955,-2.3305,27.2611,1.3200,-6.0000,0.0000,23.4559,1.3419,-6.3045,2.3305,-6.6482},
				{0.0200,32.8262,-1.5904,31.0881,1.1833,-5.7405,-2.2456,27.2851,1.2000,-6.0000,0.0000,23.4802,1.2167,-6.2595,2.2456,-6.6479},
				{0.0200,32.8464,-1.5981,31.1094,1.0676,-5.7831,-2.1329,27.3067,1.0800,-6.0000,0.0000,23.5021,1.0924,-6.2169,2.1329,-6.6477},
				{0.0200,32.8643,-1.6050,31.1285,0.9511,-5.8230,-1.9945,27.3259,0.9600,-6.0000,0.0000,23.5215,0.9689,-6.1770,1.9945,-6.6475},
				{0.0200,32.8800,-1.6110,31.1452,0.8339,-5.8597,-1.8328,27.3427,0.8400,-6.0000,0.0000,23.5384,0.8461,-6.1403,1.8328,-6.6474},
				{0.0200,32.8935,-1.6161,31.1595,0.7161,-5.8927,-1.6502,27.3571,0.7200,-6.0000,0.0000,23.5529,0.7239,-6.1073,1.6502,-6.6473},
				{0.0200,32.9047,-1.6204,31.1714,0.5977,-5.9216,-1.4491,27.3691,0.6000,-6.0000,0.0000,23.5649,0.6023,-6.0784,1.4491,-6.6473},
				{0.0200,32.9136,-1.6238,31.1810,0.4787,-5.9463,-1.2321,27.3787,0.4800,-6.0000,0.0000,23.5745,0.4813,-6.0537,1.2321,-6.6473},
				{0.0200,32.9204,-1.6263,31.1882,0.3594,-5.9663,-1.0018,27.3859,0.3600,-6.0000,0.0000,23.5818,0.3606,-6.0337,1.0018,-6.6473},
				{0.0200,32.9249,-1.6280,31.1930,0.2398,-5.9815,-0.7608,27.3907,0.2400,-6.0000,0.0000,23.5866,0.2402,-6.0185,0.7608,-6.6473},
				{0.0200,32.9271,-1.6289,31.1954,0.1199,-5.9918,-0.5117,27.3931,0.1200,-6.0000,0.0000,23.5890,0.1201,-6.0082,0.5117,-6.6473},
				{0.0200,32.9271,-1.6289,31.1954,0.0000,-5.9969,-0.2572,27.3931,-0.0000,-6.0000,0.0000,23.5890,0.0000,-6.0031,0.2572,-6.6473},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}