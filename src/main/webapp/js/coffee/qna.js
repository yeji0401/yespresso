/**
 * 질문과 답변
 */
 const qnaList = [
	{
		q: '1. 어떤 커피를 알고 싶나요? ',
		a: [
			{ answer: '다양한 커피 스타일 버츄오',
			type:['voltesso','ilcaffe','fortado','iceleggero','diavolitto','costarica',
			'solelio','halfcaffeeinato','melozio','inizio']},
			{ answer: '클래식 에스프레소 경험 오리지널', 
			 type:['paris','istanbul','roma','colombia','milanointenso','tokyolungo',
			 'stockholmlungo','corto','infinimentespresso','icefreddointenso']}			
		]
	},
	{
		q: '2. 어떤 종류의 향을 좋아하세요? ',
		a: [
			{ answer: '초콜렛', type:['voltesso','ilcaffee','fortado','corto','milanointenso','istanbul']},
			{ answer: '과좌,견과류', type:['halfcaffeeinato','melozio','costarica','paris','icefreddointenso','stockholmlungo','roma']},
			{ answer: '과일', type:['diavolitto','iceleggero','inizio','solelio','tokyolungo','infinimentespresso','colombia']}			
		]
	},
	{
		q: '3. 가벼운걸 좋아하세요? ',
		a: [
			{ answer: '산미가 적어 바디감이 풍부', type:['voltesso','ilcaffee','fortado','halfcaffeeinato','melozio','costaria','diavolitto',
			'corto','milanointenso','paris','icefreddointenso','stockholmlungo','tokyolungo','iceleggero']},
			{ answer: '산미가 높아 상큼함', type:['solelio','inizio','istanbul','roma','colombia','infinimentespresso']}			
		]
	},
	{
		q: '4. 어떤 종류의 커피 맛을 좋아하세요? ',
		a: [
			{ answer: '진한 커피', type:['voltesso','halfcaffeeinato','melozio','solelio','inizio','corto','paris','tokyolungo','infinimentespresso','colombia']},
			{ answer: '마일드 커피', type:['ilcaffee','fortado','costarica','diavolitto','iceleggero','istanbul','milanointenso','icefreddointenso','stockholmlungo','roma']}			
		]
	},
	{
		q: '5. 에스프레소가 풍부한 걸 좋아하세요? ',
		a: [
			{ answer: '클래식 에스프레소 경험 오리지널', type:['voltesso','ilcaffee','iceleggero','diavolitto','corto','milanointenso','instanbul','paris',
			'icefreddointenso','roma','colombia','infinimentespresso']},
			{ answer: '클래식 에스프레소 경험 오리지널', type:['fortado','costarica','inizio','stockholmlungo','tokyolungo']},
			{ answer: '다양한 커피 스타일 버츄오', type:['halfcaffeeinato','melozio','solelio']}			
		]
	}
]


const infoList = [
	{
		name: '볼테소 voltesso',
		desc: '산미 : 2,      강도 : 4'
	},
	{
		name: '일카페 il caffee',
		desc: '산미 : 1,      강도 : 11'
	},
	{
		name: '하프 카페나토 halfcaffeeinato',
		desc: '산미 : 1,      강도 : 5'
	},
	{
		name: '솔레리오 solerio',
		desc: '산미 : 3,      강도 : 2'
	},
	{
		name: '이니지오 inizio',
		desc: '산미 : 3,      강도 : 4'
	},
	{
		name: '멜로지오 melozio',
		desc: '산미 : 1,      강도 : 6'
	},
	{
		name: '디아볼리토 diavolitto',
		desc: '산미 : 1,      강도 : 11'
	},
	{
		name: '코스타리카 costarica',
		desc: '산미 : 1,      강도 : 7'
	},
	{
		name: '포르타도 fortado',
		desc: '산미 : 1,      강도 : 8'
	},
	{
		name: '아이스 레제로 ice leggero',
		desc: '산미 : 2,      강도 : 7'
	},
	{
		name: '인피니망 에스프레소 infiniment espresso',
		desc: '산미 : 3,      강도 : 6'
	},
	{
		name: '파리 paris',
		desc: '산미 : 2,      강도 : 6'
	},
	{
		name: '도쿄 룽고 tokyo lungo',
		desc: '산미 : 1,      강도 : 6'
	},
	{
		name: '코르토 corto',
		desc: '산미 : 1,      강도 : 5'
	},
	{
		name: '콜롬비아 colombia',
		desc: '산미 : 4,      강도 : 6'
	},
	{
		name: '밀라노 인텐소 milano intenso',
		desc: '산미 : 2,      강도 : 8'
	},
	{
		name: '스톡홀룸 룽고 stockholm lungo',
		desc: '산미 : 2,      강도 : 8'
	},
	{
		name: '아이스 프레도 인텐소 ice freddo intenso',
		desc: '산미 : 2,      강도 : 8'
	},
	{
		name: '이스탄불 instanbul',
		desc: '산미 : 4,      강도 : 8'
	},
	{
		name: '로마 roma',
		desc: '산미 : 4,      강도 : 8'
	}
]






