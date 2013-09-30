package rec;

import org.apache.mahout.cf.taste.common.TasteException;

import rec.collaborative.eval.SvdppRecEval;

public class main {
	// GUI skalierbar �ber Parameter im Konstruktor
	public static void main(String[] args) throws TasteException {
		new GUI(800);
//		SvdppRecEval evaluator = new SvdppRecEval();
//		evaluator.eval();
	}
}
