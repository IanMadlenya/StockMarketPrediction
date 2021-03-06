package pl.edu.pw.mini.msi.patterns;

import java.util.ArrayList;

import pl.edu.pw.mini.msi.utils.StockPrice;
import pl.edu.pw.mini.msi.utils.Type;

public class WhiteSpinningTop implements Pattern {

	@Override
	public double matchData(ArrayList<StockPrice> data) {
		StockPrice top = data.get(0);
		//Checks if candle is not black
		if (top.isBlack())
			return 0;
		//Checks if shadows are longer than body
		if (Math.abs(top.body())<top.upperShadow() &&
				Math.abs(top.body())<top.lowerShadow())
			return getSpinMatch(top);
		return 0;
	}
	
	private double getSpinMatch(StockPrice top)
	{
		double result = ((top.upperShadow()+top.lowerShadow())-Math.abs(top.body()*2));
		result = (result>1)  ? 1 : (result<0) ? 0 : result;
		return result;
	}

	@Override
	public String name() {
		return "White Spinning Top";
	}

	@Override
	public Type type() {
		return Type.NEUTRAL;
	}

}
