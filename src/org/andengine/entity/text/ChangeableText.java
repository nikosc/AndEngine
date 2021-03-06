package org.andengine.entity.text;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObject.DrawType;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.StringUtils;

import android.opengl.GLES20;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 18:07:06 - 08.07.2010
 */
public class ChangeableText extends Text {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ELLIPSIS = "...";
	private static final int ELLIPSIS_CHARACTER_COUNT = ChangeableText.ELLIPSIS.length();

	// ===========================================================
	// Fields
	// ===========================================================

	private int mCharacterCountCurrentText;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading) {
		this(pX, pY, pFont, pText, pLeading, DrawType.DYNAMIC);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, pLeading, DrawType.DYNAMIC, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final DrawType pDrawType) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pDrawType);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pDrawType, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final DrawType pDrawType) {
		this(pX, pY, pFont, pText, pLeading, pDrawType, pText.length() - StringUtils.countOccurrences(pText, '\n'));
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, pLeading, pDrawType, pText.length() - StringUtils.countOccurrences(pText, '\n'), pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final int pCharactersMaximum) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pCharactersMaximum);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final int pCharactersMaximum, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pCharactersMaximum, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final int pCharactersMaximum) {
		this(pX, pY, pFont, pText, pLeading, DrawType.DYNAMIC, pCharactersMaximum);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final int pCharactersMaximum, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, pLeading, DrawType.DYNAMIC, pCharactersMaximum, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final DrawType pDrawType, final int pCharactersMaximum) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pDrawType, pCharactersMaximum);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final DrawType pDrawType, final int pCharactersMaximum, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, Text.LEADING_DEFAULT, pDrawType, pCharactersMaximum, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final DrawType pDrawType, final int pCharactersMaximum) {
		this(pX, pY, pFont, pText, HorizontalAlign.LEFT, pLeading, pDrawType, pCharactersMaximum);
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final float pLeading, final DrawType pDrawType, final int pCharactersMaximum, final ShaderProgram pShaderProgram) {
		this(pX, pY, pFont, pText, HorizontalAlign.LEFT, pLeading, pDrawType, pCharactersMaximum, pShaderProgram);
	}

	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final HorizontalAlign pHorizontalAlign, final float pLeading, final DrawType pDrawType, final int pCharactersMaximum) {
		super(pX, pY, pFont, pText, pHorizontalAlign, pLeading, pDrawType, pCharactersMaximum);
		this.mCharacterCountCurrentText = pText.length() - StringUtils.countOccurrences(pText, '\n');
	}
	
	public ChangeableText(final float pX, final float pY, final IFont pFont, final String pText, final HorizontalAlign pHorizontalAlign, final float pLeading, final DrawType pDrawType, final int pCharactersMaximum, final ShaderProgram pShaderProgram) {
		super(pX, pY, pFont, pText, pHorizontalAlign, pLeading, pDrawType, pCharactersMaximum, pShaderProgram);
		this.mCharacterCountCurrentText = pText.length() - StringUtils.countOccurrences(pText, '\n');
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setText(final String pText) {
		this.setText(pText, false);
	}

	/**
	 * @param pText
	 * @param pAllowEllipsis in the case pText is longer than <code>pCharactersMaximum</code>,
	 *	which was passed to the constructor, the displayed text will end with an ellipsis ("...").
	 */
	public void setText(final String pText, final boolean pAllowEllipsis) {
		final int textCharacterCount = pText.length() - StringUtils.countOccurrences(pText, '\n');
		if(textCharacterCount > this.mCharactersMaximum) {
			if(pAllowEllipsis && this.mCharactersMaximum > ChangeableText.ELLIPSIS_CHARACTER_COUNT) {
				this.updateText(pText.substring(0, this.mCharactersMaximum - ChangeableText.ELLIPSIS_CHARACTER_COUNT).concat(ChangeableText.ELLIPSIS));
			} else {
				this.updateText(pText.substring(0, this.mCharactersMaximum));
			}
			this.mCharacterCountCurrentText = this.mCharactersMaximum;
		} else {
			this.updateText(pText);
			this.mCharacterCountCurrentText = textCharacterCount;
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void draw(final GLState pGLState, final Camera pCamera) {
		this.mTextVertexBufferObject.draw(GLES20.GL_TRIANGLES, this.mCharacterCountCurrentText * Text.VERTICES_PER_LETTER);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
