using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ImagemOcorrenciasController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public ImagemOcorrenciasController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/ImagemOcorrencias
        [HttpGet]
        public IEnumerable<ImagemOcorrencia> GetImagemOcorrencia()
        {
            return _context.ImagemOcorrencia;
        }

        // GET: api/ImagemOcorrencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetImagemOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var imagemOcorrencia = await _context.ImagemOcorrencia.FindAsync(id);

            if (imagemOcorrencia == null)
            {
                return NotFound();
            }

            return Ok(imagemOcorrencia);
        }

        // PUT: api/ImagemOcorrencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutImagemOcorrencia([FromRoute] int id, [FromBody] ImagemOcorrencia imagemOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != imagemOcorrencia.CdImagem)
            {
                return BadRequest();
            }

            _context.Entry(imagemOcorrencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ImagemOcorrenciaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/ImagemOcorrencias
        [HttpPost]
        public async Task<IActionResult> PostImagemOcorrencia([FromBody] ImagemOcorrencia imagemOcorrencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.ImagemOcorrencia.Add(imagemOcorrencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetImagemOcorrencia", new { id = imagemOcorrencia.CdImagem }, imagemOcorrencia);
        }

        // DELETE: api/ImagemOcorrencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteImagemOcorrencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var imagemOcorrencia = await _context.ImagemOcorrencia.FindAsync(id);
            if (imagemOcorrencia == null)
            {
                return NotFound();
            }

            _context.ImagemOcorrencia.Remove(imagemOcorrencia);
            await _context.SaveChangesAsync();

            return Ok(imagemOcorrencia);
        }

        private bool ImagemOcorrenciaExists(int id)
        {
            return _context.ImagemOcorrencia.Any(e => e.CdImagem == id);
        }
    }
}